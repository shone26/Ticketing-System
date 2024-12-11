import { useNavigate } from "react-router-dom";
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

function Home() {
    const navigate = useNavigate();

  const handleGetStarted = () => {
    navigate("/book-tickets"); // Navigate to the booking page or any other page you want
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
          className="bg-green-600 text-gray-50 hover:bg-green-500 rounded-md px-6 py-3 w-full sm:w-auto mx-auto"
        >
          Get Started
        </Button>
      </div>

      <div className="mt-12 text-center text-sm text-gray-300">
        <p>If you have any questions, feel free to reach out.</p>
      </div>
    </div>
  );
}

export default Home;