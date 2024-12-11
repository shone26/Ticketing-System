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

function VendorDetailPage() {
  const [numVendors, setNumVendors] = useState("");
  const [vendors, setVendors] = useState([]);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [isStep2, setIsStep2] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleVendorCountChange = (e) => {
    const value = e.target.value;
    if (value === "" || /^[0-9\b]+$/.test(value)) {
      setNumVendors(value);
    }
  };

  const handleNextStep = () => {
    if (numVendors > 0) {
      setVendors(
        Array.from({ length: numVendors }, () => ({
          firstName: "",
          lastName: "",
          releaseTicketAmount: "",
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
    setNumVendors("");
    setVendors([]);
    setIsStep2(false);
    setIsSubmitted(false);
  };

  const handleSubmit = async () => {
    // Check for empty fields in each vendor
    for (const vendor of vendors) {
      if (!vendor.firstName || !vendor.lastName || !vendor.releaseTicketAmount) {
        alert("Please fill in all the fields for each vendor.");
        return; // Stop the form submission if any field is empty
      }
    }

    setIsLoading(true);
    const data = vendors.map(({ firstName, lastName, releaseTicketAmount }) => ({
      firstName,
      lastName,
      releaseTicketAmount,
    }));

    try {
      const response = await axios.post("http://localhost:8080/api/vendor/add-vendor", data, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("Form submitted with:", response.data);
      setIsSubmitted(true);
      navigate("/customer-details");
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
            <CardTitle className="text-2xl font-semibold text-green-400">Vendor Form</CardTitle>
            <CardDescription className="text-sm text-gray-400">
              Please provide the vendor details below
            </CardDescription>
          </CardHeader>

          <CardContent className="space-y-6">
            {!isStep2 ? (
              <div className="space-y-6 mt-4">
                <div>
                  <Label htmlFor="numVendors" className="text-sm font-medium text-gray-300">
                    How many vendors would you like to add?
                  </Label>
                  <Input
                    type="number"
                    id="numVendors"
                    value={numVendors}
                    onChange={handleVendorCountChange}
                    className="bg-gray-800 text-gray-200 border-gray-700 rounded-md focus:ring-2 focus:ring-green-400 mt-2"
                    placeholder="Enter number of vendors"
                  />
                </div>
                <Button
                  onClick={handleNextStep}
                  className="bg-green-600 text-gray-50 hover:bg-green-500 rounded-md px-6 py-3 mt-4 w-full"
                  disabled={numVendors <= 0}
                >
                  Next
                </Button>
              </div>
            ) : (
              <div className="space-y-6 mt-6">
                {vendors.map((vendor, index) => (
                  <div
                    key={index}
                    className="p-6 border border-gray-700 rounded-md bg-gray-800 text-gray-50"
                  >
                    <h3 className="text-lg font-semibold text-green-400 mb-4 text-center">
                      Vendor {index + 1}
                    </h3>
                    <div className="space-y-4">
                      <div>
                        <Label htmlFor={`firstName-${index}`} className="text-sm font-medium text-gray-300">
                          First Name:
                        </Label>
                        <Input
                          id={`firstName-${index}`}
                          value={vendor.firstName}
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
                          value={vendor.lastName}
                          onChange={(e) => handleInputChange(index, "lastName", e.target.value)}
                          className="bg-gray-800 text-gray-200 border-gray-700 rounded-md focus:ring-2 focus:ring-green-400 w-full"
                          placeholder="Enter last name"
                        />
                      </div>

                      <div>
                        <Label htmlFor={`releaseTicketAmount-${index}`} className="text-sm font-medium text-gray-300">
                          Release Ticket Amount:
                        </Label>
                        <Input
                          id={`releaseTicketAmount-${index}`}
                          value={vendor.releaseTicketAmount}
                          onChange={(e) =>
                            handleInputChange(index, "releaseTicketAmount", e.target.value)
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
                      Please double-check the vendor details before submitting.
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
            <Link to="/configuration" className="text-green-400 hover:underline">
              click here
            </Link>
          </div>
        </form>
      </Card>
    </div>
  );
}

export default VendorDetailPage;
