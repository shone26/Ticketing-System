import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { CardFooter } from "@/components/ui/card";
import { io } from "socket.io-client";

function LogViewer() {
  const navigate = useNavigate();

  const [logs, setLogs] = useState([]);
  const [isConnected, setIsConnected] = useState(false);
  const [socket, setSocket] = useState(null);
  const [isError, setIsError] = useState(false); // To track connection errors
  const [isLoading, setIsLoading] = useState(false); // To track if the system is being started

  // Establish WebSocket connection to Spring Boot backend
  useEffect(() => {
    const socketConnection = io("ws://localhost:8080"); // Replace with your WebSocket server URL

    socketConnection.on("connect", () => {
      setIsConnected(true);
      setIsError(false); // Reset error state on successful connection
    });

    socketConnection.on("disconnect", () => {
      setIsConnected(false);
    });

    socketConnection.on("connect_error", (error) => {
      console.error("Connection failed: ", error);
      setIsError(true); // Set error state on connection failure
      setIsConnected(false);
    });

    socketConnection.on("ticketEvent", (logMessage) => {
      setLogs((prevLogs) => [...prevLogs, logMessage]);
    });

    setSocket(socketConnection);

    // Cleanup on component unmount
    return () => {
      socketConnection.disconnect();
    };
  }, []);

  // Handler for starting the ticket system by calling the backend API
  const handleGetStarted = async () => {
    try {
      setIsLoading(true);
      const response = await fetch("http://localhost:8080/api/system/start", {
        method: "POST", // Assuming the API accepts POST requests to start the system
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        console.log("System started successfully");
        navigate("/book-tickets"); // Navigate to the booking page or any other page you want
      } else {
        console.error("Failed to start the system");
      }
    } catch (error) {
      console.error("Error starting the system: ", error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleStart = () => {
    if (socket) {
      socket.emit("startTicketing"); // Emitting start event to the backend
    }
  };

  return (
    <div className="bg-gradient-to-br from-gray-800 via-gray-900 to-black text-gray-200 min-h-screen flex flex-col items-center justify-center p-8">
      <div className="text-center mb-12">
        <h1 className="text-3xl font-semibold text-green-400 mb-2">Ticket Booking System</h1>
        <p className="text-sm text-gray-400">Welcome to the ticket booking platform</p>
      </div>

      <div className="flex flex-col gap-6">
        <Button
          onClick={handleGetStarted}
          disabled={isLoading}
          className="bg-green-600 text-gray-50 hover:bg-green-500 rounded-md px-6 py-3 w-full sm:w-auto mx-auto"
        >
          {isLoading ? "Starting..." : "Get Started"}
        </Button>
      </div>

      <div className="mt-12 w-full">
        <Card className="bg-gray-900 text-gray-200 border border-gray-700">
          <CardHeader>
            <CardTitle className="text-lg font-semibold text-green-400">Ticket Sell and Purchase Log</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="overflow-y-auto h-48">
              <ul className="list-none p-0">
                {logs.map((log, index) => (
                  <li key={index} className="text-sm text-gray-300 mb-2">
                    {log}
                  </li>
                ))}
              </ul>
            </div>
          </CardContent>
          <CardFooter>
            <Button
              onClick={handleStart}
              disabled={!isConnected || isError} // Disable if not connected or if error
              className="bg-green-600 text-gray-50 hover:bg-green-500 rounded-md px-6 py-2 w-full sm:w-auto mx-auto"
            >
              {isConnected ? "Start Ticketing" : isError ? "Connection Error" : "Connecting..."}
            </Button>
          </CardFooter>
        </Card>
      </div>

      <div className="mt-12 text-center text-sm text-gray-300">
        <p>If you have any questions, feel free to reach out.</p>
      </div>
    </div>
  );
}

export default LogViewer;
