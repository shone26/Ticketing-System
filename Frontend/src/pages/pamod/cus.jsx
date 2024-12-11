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

function PamodCustomerDetailPage() {
  const [numCustomers, setNumCustomers] = useState(0);
  const [customers, setCustomers] = useState([]);
  const [isStep2, setIsStep2] = useState(false);
  const [isSubmitted, setIsSubmitted] = useState(false);

  const handleCustomerCountChange = (e) => {
    const value = Number(e.target.value);
    setNumCustomers(value);
  };

  const handleNextStep = () => {
    if (numCustomers > 0) {
      setCustomers(
        Array.from({ length: numCustomers }, () => ({
          customerName: "",
          customerRetrievalRate: "",
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
    const data = customers.map(({ customerName, customerRetrievalRate }) => ({
      customerName,
      customerRetrievalRate,
    }));

    try {
      const response = await axios.post("http://localhost:8080/add-customer", data, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("Form submitted with:", response.data);
      setIsSubmitted(true);
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-indigo-700 via-purple-800 to-gray-900">
      <Card className="w-full max-w-4xl bg-white text-black rounded-lg shadow-2xl">
        <form>
          {/* Header */}
          <CardHeader className="text-center bg-gradient-to-r from-purple-600 to-indigo-600 text-white rounded-t-lg py-6">
            <CardTitle className="text-4xl font-bold">Customer Details</CardTitle>
            <CardDescription className="mt-2 text-lg">
              Enter customer details and ticket retrieval amounts.
            </CardDescription>
          </CardHeader>

          {/* Content */}
          <CardContent className="p-8 space-y-6">
            {!isStep2 ? (
              <div className="space-y-4 text-center">
                <Label
                  htmlFor="numCustomers"
                  className="block text-xl font-medium text-gray-800"
                >
                  How many customers do you want to add?
                </Label>
                <Input
                  type="number"
                  id="numCustomers"
                  min="1"
                  value={numCustomers}
                  onChange={handleCustomerCountChange}
                  className="w-full max-w-md mx-auto px-4 py-3 border rounded-lg shadow focus:ring-2 focus:ring-indigo-500 focus:outline-none"
                />
                <Button
                  onClick={handleNextStep}
                  className="w-full max-w-md mx-auto px-6 py-3 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-all shadow"
                >
                  Next
                </Button>
              </div>
            ) : (
              <div className="space-y-6">
                {customers.map((customer, index) => (
                  <div
                    key={index}
                    className="p-6 bg-gray-100 rounded-lg shadow-md border"
                  >
                    <h3 className="text-lg font-semibold text-gray-800 text-center">
                      Customer {index + 1}
                    </h3>
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-4">
                      <div>
                        <Label
                          htmlFor={`customerName-${index}`}
                          className="block text-sm font-medium text-gray-700"
                        >
                          Customer Name
                        </Label>
                        <Input
                          id={`customerName-${index}`}
                          value={customer.customerName}
                          onChange={(e) =>
                            handleInputChange(index, "customerName", e.target.value)
                          }
                          className="w-full px-4 py-3 border rounded-lg shadow focus:ring-2 focus:ring-indigo-500 focus:outline-none"
                        />
                      </div>
                      <div>
                        <Label
                          htmlFor={`customerRetrievalRate-${index}`}
                          className="block text-sm font-medium text-gray-700"
                        >
                          Ticket Retrieval Amount
                        </Label>
                        <Input
                          id={`customerRetrievalRate-${index}`}
                          value={customer.customerRetrievalRate}
                          onChange={(e) =>
                            handleInputChange(
                              index,
                              "customerRetrievalRate",
                              e.target.value
                            )
                          }
                          type="number"
                          min="0"
                          className="w-full px-4 py-3 border rounded-lg shadow focus:ring-2 focus:ring-indigo-500 focus:outline-none"
                        />
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </CardContent>

          {/* Footer */}
          <CardFooter className="p-8 flex justify-between items-center">
            <Button
              variant="outline"
              className="px-6 py-3 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 shadow"
              onClick={handleReset}
            >
              Reset
            </Button>
            {isStep2 && (
              <AlertDialog>
                <AlertDialogTrigger asChild>
                  <Button className="px-6 py-3 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 shadow">
                    Submit
                  </Button>
                </AlertDialogTrigger>
                <AlertDialogContent>
                  <AlertDialogHeader>
                    <AlertDialogTitle>
                      Are you sure you want to submit?
                    </AlertDialogTitle>
                    <AlertDialogDescription>
                      Ensure all customer details are correct before submitting.
                    </AlertDialogDescription>
                  </AlertDialogHeader>
                  <AlertDialogFooter>
                    <AlertDialogCancel>Cancel</AlertDialogCancel>
                    <AlertDialogAction onClick={handleSubmit}>
                      Confirm
                    </AlertDialogAction>
                  </AlertDialogFooter>
                </AlertDialogContent>
              </AlertDialog>
            )}
          </CardFooter>

          {isSubmitted && (
            <div className="text-center mt-4 text-green-500 font-semibold text-lg">
              Form successfully submitted!
            </div>
          )}

          <div className="mt-8 text-center text-sm text-gray-600">
            Want to use an existing configuration?{" "}
            <Link
              to="/configuration"
              className="text-indigo-600 hover:underline font-medium"
            >
              Click here
            </Link>
          </div>
        </form>
      </Card>
    </div>
  );
}

export default PamodCustomerDetailPage;
