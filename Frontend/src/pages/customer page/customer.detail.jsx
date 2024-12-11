import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
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
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
  AlertDialogFooter,
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
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleCustomerCountChange = (e) => {
    const value = Number(e.target.value);
    setNumCustomers(value);
  };

  const handleNextStep = () => {
    if (numCustomers > 0) {
      setCustomers(
        Array.from({ length: numCustomers }, () => ({
          firstName: "",
          lastName: "",
          retrieveTicketAmount: "",
        }))
      );
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
    setIsLoading(true);
    const data = customers.map(({ firstName, lastName, retrieveTicketAmount }) => ({
      firstName,
      lastName,
      retrieveTicketAmount,
    }));

    try {
      const response = await axios.post("http://localhost:8080/api/customer/add-customer", data, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("Form submitted with:", response.data);
      setIsSubmitted(true);
      navigate("/log");
    } catch (error) {
      console.error("Error submitting form:", error);
    }

    setIsLoading(false);
  };

  return (
    <div className="bg-gradient-to-br from-gray-800 via-gray-900 to-black text-gray-200 min-h-screen flex items-center justify-center p-6">
      <Card className="w-full sm:w-[420px] bg-gray-900 text-gray-50 rounded-xl shadow-xl p-8">
        <form>
          <CardHeader className="text-center border-b border-gray-700 pb-6">
            <CardTitle className="text-2xl font-semibold text-green-400">Customer Form</CardTitle>
            <CardDescription className="text-sm text-gray-400">
              Fill out customer details below
            </CardDescription>
          </CardHeader>

          <CardContent className="space-y-6">
            {!isStep2 ? (
              <div className="space-y-6 mt-4">
                <div>
                  <Label htmlFor="numCustomers" className="text-sm font-medium text-gray-300">
                    How many customers do you want to add?
                  </Label>
                  <Input
                    type="number"
                    id="numCustomers"
                    min="1"
                    value={numCustomers || ""}
                    onChange={handleCustomerCountChange}
                    className="bg-gray-800 text-gray-200 border-gray-700 rounded-md focus:ring-2 focus:ring-green-400 mt-2"
                    placeholder="Enter number of customers"
                  />
                </div>
                <Button
                  onClick={handleNextStep}
                  className="bg-green-600 text-gray-50 hover:bg-green-500 rounded-md px-6 py-3 mt-4 w-full"
                  disabled={numCustomers <= 0}
                >
                  Next
                </Button>
              </div>
            ) : (
              <div className="space-y-6 mt-6">
                {customers.map((customer, index) => (
                  <div
                    key={index}
                    className="p-6 border border-gray-700 rounded-md bg-gray-800 text-gray-50"
                  >
                    <h3 className="text-lg font-semibold text-green-400 mb-4 text-center">
                      Customer {index + 1}
                    </h3>
                    <div className="space-y-4">
                      <div>
                        <Label htmlFor={`firstName-${index}`} className="text-sm font-medium text-gray-300">
                          First Name:
                        </Label>
                        <Input
                          id={`firstName-${index}`}
                          value={customer.firstName}
                          onChange={(e) => handleInputChange(index, "firstName", e.target.value)}
                          className="bg-gray-800 text-gray-200 border-gray-700 rounded-md focus:ring-2 focus:ring-green-400 w-full"
                          placeholder="Enter first name"
                        />
                      </div>

                      <div>
                        <Label htmlFor={`lastName-${index}`} className="text-sm font-medium text-gray-300">
                          Last Name:
                        </Label>
                        <Input
                          id={`lastName-${index}`}
                          value={customer.lastName}
                          onChange={(e) => handleInputChange(index, "lastName", e.target.value)}
                          className="bg-gray-800 text-gray-200 border-gray-700 rounded-md focus:ring-2 focus:ring-green-400 w-full"
                          placeholder="Enter last name"
                        />
                      </div>

                      <div>
                        <Label
                          htmlFor={`retrieveTicketAmount-${index}`}
                          className="text-sm font-medium text-gray-300"
                        >
                          Retrieve Ticket Amount:
                        </Label>
                        <Input
                          id={`retrieveTicketAmount-${index}`}
                          value={customer.retrieveTicketAmount}
                          onChange={(e) =>
                            handleInputChange(index, "retrieveTicketAmount", e.target.value)
                          }
                          className="bg-gray-800 text-gray-200 border-gray-700 rounded-md focus:ring-2 focus:ring-green-400 w-full"
                          type="number"
                          min="0"
                          placeholder="Enter ticket amount"
                        />
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </CardContent>

          <CardFooter className="flex justify-between items-center mt-6">
            <Button
              variant="outline"
              className="bg-gray-700 text-gray-50 hover:bg-gray-600 rounded-md px-6 py-3 w-full sm:w-auto"
              onClick={handleReset}
            >
              Reset
            </Button>

            {isStep2 && (
              <AlertDialog>
                <AlertDialogTrigger asChild>
                  <Button
                    className="bg-green-600 text-gray-50 hover:bg-green-500 rounded-md px-6 py-3 w-full sm:w-auto"
                    disabled={isLoading}
                  >
                    {isLoading ? "Submitting..." : "Submit"}
                  </Button>
                </AlertDialogTrigger>
                <AlertDialogContent>
                  <AlertDialogHeader>
                    <AlertDialogTitle>Confirm Submission</AlertDialogTitle>
                    <AlertDialogDescription>
                      Please double-check the customer details before submitting.
                    </AlertDialogDescription>
                  </AlertDialogHeader>
                  <AlertDialogFooter>
                    <AlertDialogCancel>Cancel</AlertDialogCancel>
                    <AlertDialogAction onClick={handleSubmit}>Confirm</AlertDialogAction>
                  </AlertDialogFooter>
                </AlertDialogContent>
              </AlertDialog>
            )}
          </CardFooter>

          {isSubmitted && (
            <div className="text-center mt-6 text-green-500">
              <strong>Form successfully submitted!</strong>
            </div>
          )}

          <div className="mt-4 text-center text-sm text-gray-300">
            To view previous configurations,{" "}
            <Link to="/log" className="text-green-400 hover:underline">
              click here
            </Link>
          </div>
        </form>
      </Card>
    </div>
  );
}

export default CustomerDetailPage;
