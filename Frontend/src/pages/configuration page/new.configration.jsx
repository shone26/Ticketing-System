import { useState } from "react";
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
  } from "@/components/ui/alert-dialog"

  
import { Label } from "@radix-ui/react-label";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

function NewConfig() {
  const [ticketAmount, setTicketAmount] = useState("");
  const [releaseRate, setReleaseRate] = useState("");
  const [retrievalRate, setRetrievalRate] = useState("");
  const [ticketCapacity, setTicketCapacity] = useState("");

  // Handle input change and validate for integer input only
  const handleInputChange = (e, setState) => {
    const value = e.target.value;
    // Check if the value is an integer
    if (/^\d*$/.test(value)) {
      setState(value); // Update state only if the input is valid integer
    }
  };

  const handleReset = () => {
    setTicketAmount("");
    setReleaseRate("");
    setRetrievalRate("");
    setTicketCapacity("");
  };

  return (
    <div>
      <div className="dark:bg-gray-900 dark:text-white bg-white text-black min-h-screen flex items-center justify-center">
        <Card className="w-[350px]">
          <CardHeader className="text-center">
            <CardTitle className="text-lg">Configuration Setup</CardTitle>
            <CardDescription>Fill the following Configuration Setting</CardDescription>
          </CardHeader>
          <CardContent>
            <form onSubmit={handleSubmit}>
              <div className="grid w-full items-center gap-4">
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="ticketAmount">Enter total tickets available: </Label>
                  <Input
                    id="ticketAmount"
                    value={formData.ticketAmount}
                    onChange={(e) => handleInputChange(e, setTicketAmount)} // Apply validation
                    placeholder="Enter ticket amount"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="releaseRate">
                    Enter ticket release rate per ticket (How much tickets for a minute):{" "}
                  </Label>
                  <Input
                    id="releaseRate"
                    value={formData.releaseRate}
                    onChange={(e) => handleInputChange(e, setReleaseRate)} // Apply validation
                    placeholder="Enter release rate"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="retrievalRate">
                    Enter customer retrieval rate per ticket (How much tickets for a minute):{" "}
                  </Label>
                  <Input
                    id="retrievalRate"
                    value={formData.retrievalRate}
                    onChange={(e) => handleInputChange(e, setRetrievalRate)} // Apply validation
                    placeholder="Enter retrieve rate"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="ticketCapacity">Enter max ticket capacity: </Label>
                  <Input
                    id="ticketCapacity"
                    value={formData.ticketCapacity}
                    onChange={(e) => handleInputChange(e, setTicketCapacity)} // Apply validation
                    placeholder="Enter ticket capacity"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
              </div>
            </form>
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
            This action cannot be undone. This will permanently delete previous configuration and replace these configration settings to the servers.
          </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>Cancel</AlertDialogCancel>
          <AlertDialogAction>Continue</AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
         {/* <Button className="dark:bg-gray-700 dark:text-white">Setup</Button> */}
          </CardFooter>
          <div className="mt-4 text-center text-sm pb-6">
            To use previously added Configration -{" "}
            <Link to={"/sign-up"}>Click here</Link>
          </div>
        </Card>
      </div>
    </div>
  );
}

export default NewConfig;
