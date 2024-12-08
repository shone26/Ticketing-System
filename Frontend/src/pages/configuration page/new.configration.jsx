import { useState } from "react";
import axios from "axios";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";

import { Label } from "@radix-ui/react-label";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

function NewConfig() {

  
  // State variables for form data
  const [totalTickets, setTotalTickets] = useState("");
  const [ticketReleaseRate, setTicketReleaseRate] = useState("");
  const [ticketRetrievalRate, setTicketRetrievalRate] = useState("");
  const [maximumTicketCapacity, setMaximumTicketCapacity] = useState("");

  // Handle input change and validate for integer input only
  const handleInputChange = (e, setState) => {
    const value = e.target.value;
    // Check if the value is an integer
    if (/^\d*$/.test(value)) {
      setState(value); // Update state only if the input is valid integer
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
  
    const data = {
      totalTickets,
      ticketReleaseRate,
      ticketRetrievalRate,
      maximumTicketCapacity,
    };
  
    try {
      const response = await axios.post("http://localhost:8080/write-config-json", data, {
        headers: {
          'Content-Type': 'application/json', // Ensure correct content type
        },
      });
      console.log("Form submitted with:", response.data);
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };
  

  // Reset form data
  const handleReset = () => {
    setTotalTickets("");
    setTicketReleaseRate("");
    setTicketRetrievalRate("");
    setMaximumTicketCapacity("");
  };

  return (
    <div className="dark:bg-gray-900 dark:text-white bg-white text-black min-h-screen flex items-center justify-center">
      <Card className="w-[350px]">
        <form onSubmit={onSubmit}>
          <CardHeader className="text-center">
            <CardTitle className="text-lg">Configuration Setup</CardTitle>
            <CardDescription>Fill the following Configuration Setting</CardDescription>
          </CardHeader>
          <CardContent>
            <div className="grid w-full items-center gap-4">
              <div className="flex flex-col space-y-1.5">
                <Label htmlFor="totalTickets">Enter total tickets available: </Label>
                <Input
                  id="totalTickets"
                  value={totalTickets} // Use state variable directly
                  onChange={(e) => handleInputChange(e, setTotalTickets)} // Apply validation
                  placeholder="Enter ticket amount"
                  className="dark:bg-gray-800 dark:text-white"
                />
              </div>
              <div className="flex flex-col space-y-1.5">
                <Label htmlFor="ticketReleaseRate">
                  Enter ticket release rate per ticket (How much tickets for a minute):{" "}
                </Label>
                <Input
                  id="ticketReleaseRate"
                  value={ticketReleaseRate} // Use state variable directly
                  onChange={(e) => handleInputChange(e, setTicketReleaseRate)} // Apply validation
                  placeholder="Enter release rate"
                  className="dark:bg-gray-800 dark:text-white"
                />
              </div>
              <div className="flex flex-col space-y-1.5">
                <Label htmlFor="ticketRetrievalRate">
                  Enter customer retrieval rate per ticket (How much tickets for a minute):{" "}
                </Label>
                <Input
                  id="ticketRetrievalRate"
                  value={ticketRetrievalRate} // Use state variable directly
                  onChange={(e) => handleInputChange(e, setTicketRetrievalRate)} // Apply validation
                  placeholder="Enter retrieve rate"
                  className="dark:bg-gray-800 dark:text-white"
                />
              </div>
              <div className="flex flex-col space-y-1.5">
                <Label htmlFor="maximumTicketCapacity">Enter max ticket capacity: </Label>
                <Input
                  id="maximumTicketCapacity"
                  value={maximumTicketCapacity} // Use state variable directly
                  onChange={(e) => handleInputChange(e, setMaximumTicketCapacity)} // Apply validation
                  placeholder="Enter ticket capacity"
                  className="dark:bg-gray-800 dark:text-white"
                />
              </div>
            </div>
          </CardContent>
          <CardFooter className="flex justify-between pb-1">
            <Button
              variant="outline"
              className="dark:bg-gray-800 dark:text-white"
              onClick={handleReset}
            >
              Reset
            </Button>
            <AlertDialog>
              <AlertDialogTrigger asChild>
                <Button className="dark:bg-gray-700 dark:text-white">Setup</Button>
              </AlertDialogTrigger>
              <AlertDialogContent>
                <AlertDialogHeader>
                  <AlertDialogTitle>Are you absolutely sure?</AlertDialogTitle>
                  <AlertDialogDescription>
                    This action cannot be undone. This will permanently delete previous configuration and replace these configuration settings on the server.
                  </AlertDialogDescription>
                </AlertDialogHeader>
                <AlertDialogFooter>
                  <AlertDialogCancel>Cancel</AlertDialogCancel>
                  <AlertDialogAction
                    onClick={onSubmit} // Trigger form submission when "Continue" is clicked
                  >
                    Continue
                  </AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog>
          </CardFooter>
          <div className="mt-4 text-center text-sm pb-6">
            To use previously added Configuration -{" "}
            <Link to={"/configuration"}>Click here</Link>
          </div>
        </form>
      </Card>
    </div>
  );
}

export default NewConfig;
