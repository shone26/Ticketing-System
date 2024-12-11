import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle, CardFooter } from "@/components/ui/card";
import { Label } from "@radix-ui/react-label";
import { Input } from "@/components/ui/input";
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger } from "@/components/ui/alert-dialog";

function CustomerDetailPage() {
  const [numCustomers, setNumCustomers] = useState(0); // Number of customers to add
  const [customers, setCustomers] = useState([]); // Data for customers (firstName, lastName, isVip)
  const [step, setStep] = useState(1); // Step 1: Enter number of customers, Step 2: Enter customer details

  // Handle the number of customers input change
  const handleCustomerCountChange = (e) => {
    const value = Math.max(0, parseInt(e.target.value));
    setNumCustomers(value);
  };

  // Handle "Next" button to generate customer input forms
  const handleNext = () => {
    if (numCustomers > 0) {
      setCustomers(Array.from({ length: numCustomers }, () => ({
        firstName: "",
        lastName: "",
        isVip: "no"
      })));
      setStep(2); // Go to Step 2 where customers' details are entered
    }
  };

  // Handle input change for each customer's data
  const handleInputChange = (index, field, value) => {
    const updatedCustomers = [...customers];
    updatedCustomers[index][field] = value;
    setCustomers(updatedCustomers);
  };

  // Handle reset (clear all customer data)
  const handleReset = () => {
    setNumCustomers(0);
    setCustomers([]);
    setStep(1);
  };

  // Handle form submission (e.g., send data to server)
  const handleSubmit = () => {
    console.log("Submitted customer data:", customers);
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
            {step === 1 ? (
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
                <Button
                  onClick={handleNext}
                  className="dark:bg-gray-800 dark:text-white"
                  disabled={numCustomers === 0}
                >
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
                        onChange={(e) => handleInputChange(index, "firstName", e.target.value)}
                        className="dark:bg-gray-800 dark:text-white"
                      />

                      <Label htmlFor={`lastName-${index}`}>Last Name:</Label>
                      <Input
                        id={`lastName-${index}`}
                        value={customer.lastName}
                        onChange={(e) => handleInputChange(index, "lastName", e.target.value)}
                        className="dark:bg-gray-800 dark:text-white"
                      />

                      <div>
                        <Label>Is VIP:</Label>
                        <div className="flex space-x-4">
                          <label>
                            <input
                              type="radio"
                              name={`vip-${index}`}
                              value="yes"
                              checked={customer.isVip === "yes"}
                              onChange={() => handleInputChange(index, "isVip", "yes")}
                            />
                            Yes
                          </label>
                          <label>
                            <input
                              type="radio"
                              name={`vip-${index}`}
                              value="no"
                              checked={customer.isVip === "no"}
                              onChange={() => handleInputChange(index, "isVip", "no")}
                            />
                            No
                          </label>
                        </div>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </CardContent>

          <CardFooter className="flex justify-between pb-1">
            {/* Reset button */}
            <Button
              variant="outline"
              className="dark:bg-gray-800 dark:text-white"
              onClick={handleReset}
            >
              Reset
            </Button>

            {/* Submit Button */}
            <AlertDialog>
              <AlertDialogTrigger asChild>
                <Button className="dark:bg-gray-700 dark:text-white">
                  Submit
                </Button>
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
                  <AlertDialogAction
                    onClick={handleSubmit}
                  >
                    Continue
                  </AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog>
          </CardFooter>
        </form>
      </Card>
    </div>
  );
}

export default CustomerDetailPage;
