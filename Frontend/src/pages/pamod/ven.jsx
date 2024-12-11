import { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
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

function PamodVendorDetailPage() {
    const [numVendors, setNumVendors] = useState(0);
    const [vendors, setVendors] = useState([]);
    const [isStep2, setIsStep2] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);
  
    const handleVendorCountChange = (e) => {
      const value = Number(e.target.value);
      setNumVendors(value);
    };
  
    const handleNextStep = () => {
      if (numVendors > 0) {
        setVendors(
          Array.from({ length: numVendors }, () => ({
            vendorName: "",
            ticketReleaseAmount: "",
          }))
        );
        setIsStep2(true);
      }
    };
  
    const handleInputChange = (index, field, value) => {
      const updatedVendors = [...vendors];
      updatedVendors[index][field] = value;
      setVendors(updatedVendors);
    };
  
    const handleReset = () => {
      setNumVendors(0);
      setVendors([]);
      setIsStep2(false);
      setIsSubmitted(false);
    };
  
    const handleSubmit = async () => {
      const data = vendors.map(({ vendorName, ticketReleaseAmount }) => ({
        vendorName,
        ticketReleaseAmount,
      }));
  
      try {
        const response = await axios.post("http://localhost:8080/add-vendor", data, {
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
              <CardTitle className="text-4xl font-bold">Vendor Details</CardTitle>
              <CardDescription className="mt-2 text-lg">
                Enter vendor details and ticket release amounts.
              </CardDescription>
            </CardHeader>
  
            {/* Content */}
            <CardContent className="p-8 space-y-6">
              {!isStep2 ? (
                <div className="space-y-4 text-center">
                  <Label
                    htmlFor="numVendors"
                    className="block text-xl font-medium text-gray-800"
                  >
                    How many vendors do you want to add?
                  </Label>
                  <Input
                    type="number"
                    id="numVendors"
                    min="1"
                    value={numVendors}
                    onChange={handleVendorCountChange}
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
                  {vendors.map((vendor, index) => (
                    <div
                      key={index}
                      className="p-6 bg-gray-100 rounded-lg shadow-md border"
                    >
                      <h3 className="text-lg font-semibold text-gray-800 text-center">
                        Vendor {index + 1}
                      </h3>
                      <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-4">
                        <div>
                          <Label
                            htmlFor={`vendorName-${index}`}
                            className="block text-sm font-medium text-gray-700"
                          >
                            Vendor Name
                          </Label>
                          <Input
                            id={`vendorName-${index}`}
                            value={vendor.vendorName}
                            onChange={(e) =>
                              handleInputChange(index, "vendorName", e.target.value)
                            }
                            className="w-full px-4 py-3 border rounded-lg shadow focus:ring-2 focus:ring-indigo-500 focus:outline-none"
                          />
                        </div>
                        <div>
                          <Label
                            htmlFor={`ticketReleaseAmount-${index}`}
                            className="block text-sm font-medium text-gray-700"
                          >
                            Ticket Release Amount
                          </Label>
                          <Input
                            id={`ticketReleaseAmount-${index}`}
                            value={vendor.ticketReleaseAmount}
                            onChange={(e) =>
                              handleInputChange(index, "ticketReleaseAmount", e.target.value)
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
                        Ensure all vendor details are correct before submitting.
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

export default PamodVendorDetailPage;
