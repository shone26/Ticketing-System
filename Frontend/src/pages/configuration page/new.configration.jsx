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
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
  AlertDialogFooter,
} from "@/components/ui/alert-dialog";
import { Label } from "@radix-ui/react-label";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

function NewConfig() {
  const [totalTickets, setTotalTickets] = useState("");
  const [ticketReleaseRate, setTicketReleaseRate] = useState("");
  const [ticketRetrievalRate, setTicketRetrievalRate] = useState("");
  const [maximumTicketCapacity, setMaximumTicketCapacity] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errors, setErrors] = useState({
    totalTickets: false,
    ticketReleaseRate: false,
    ticketRetrievalRate: false,
    maximumTicketCapacity: false,
  });
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  const handleInputChange = (e, setState) => {
    const value = e.target.value;
    const id = e.target.id;
    if (/^\d*$/.test(value)) {
      setState(value);
      setErrors({ ...errors, [id]: false });
    } else {
      setErrors({ ...errors, [id]: true });
    }
  };

  const validateForm = () => {
    const newErrors = {
      totalTickets: !totalTickets || isNaN(totalTickets),
      ticketReleaseRate: !ticketReleaseRate || isNaN(ticketReleaseRate),
      ticketRetrievalRate: !ticketRetrievalRate || isNaN(ticketRetrievalRate),
      maximumTicketCapacity: !maximumTicketCapacity || isNaN(maximumTicketCapacity),
    };
    setErrors(newErrors);
    return !Object.values(newErrors).includes(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    setIsSubmitting(true);
    const data = {
      totalTickets,
      ticketReleaseRate,
      ticketRetrievalRate,
      maximumTicketCapacity,
    };

    try {
      const response = await axios.post("http://localhost:8080/api/config/write-config-json", data, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      console.log("Form submitted with:", response.data);
      setIsDialogOpen(false);
    } catch (error) {
      console.error("Error submitting form:", error);
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleReset = () => {
    setTotalTickets("");
    setTicketReleaseRate("");
    setTicketRetrievalRate("");
    setMaximumTicketCapacity("");
    setErrors({
      totalTickets: false,
      ticketReleaseRate: false,
      ticketRetrievalRate: false,
      maximumTicketCapacity: false,
    });
  };

  const isFormValid =
    !Object.values(errors).includes(true) &&
    totalTickets &&
    ticketReleaseRate &&
    ticketRetrievalRate &&
    maximumTicketCapacity;

  return (
    <div className="bg-gradient-to-br from-gray-800 via-gray-900 to-black text-gray-200 min-h-screen flex items-center justify-center">
      <Card className="w-full sm:w-[400px] bg-gray-900 text-gray-50 rounded-xl shadow-xl p-8">
        <form onSubmit={handleSubmit}>
          <CardHeader className="text-center border-b border-gray-700 pb-6">
            <CardTitle className="text-2xl font-semibold text-green-400">
              Configuration Setup
            </CardTitle>
            <CardDescription className="text-sm text-gray-400 mt-2">
              Fill in the following Configuration Settings. Make sure all fields are valid before submitting.
            </CardDescription>
          </CardHeader>
          <CardContent>
            <div className="space-y-6 mt-3">
              {[{
                id: "totalTickets",
                label: "Total Tickets Available",
                placeholder: "Enter ticket amount",
                value: totalTickets,
                setValue: setTotalTickets,
                helperText: "Enter the total number of tickets already available in the Ticket Pool.",
              }, {
                id: "ticketReleaseRate",
                label: "Ticket Release Rate (per minute)",
                placeholder: "Enter release rate",
                value: ticketReleaseRate,
                setValue: setTicketReleaseRate,
                helperText: "Set how many tickets are released per minute.",
              }, {
                id: "ticketRetrievalRate",
                label: "Customer Retrieval Rate (per minute)",
                placeholder: "Enter retrieve rate",
                value: ticketRetrievalRate,
                setValue: setTicketRetrievalRate,
                helperText: "Define how many tickets, customers can retrieve per minute.",
              }, {
                id: "maximumTicketCapacity",
                label: "Maximum Ticket Capacity",
                placeholder: "Enter ticket capacity",
                value: maximumTicketCapacity,
                setValue: setMaximumTicketCapacity,
                helperText: "The maximum number of tickets that can be sold at any given time.",
              }].map(({ id, label, placeholder, value, setValue, helperText }) => (
                <div key={id}>
                  <Label htmlFor={id} className="text-gray-300 text-sm font-medium">
                    {label}
                  </Label>
                  <Input
                    id={id}
                    value={value}
                    onChange={(e) => handleInputChange(e, setValue)}
                    placeholder={placeholder}
                    className={`w-full bg-gray-800 text-gray-50 border-2 rounded-md mt-2 ${
                      errors[id] ? "border-red-500" : "border-gray-600"
                    } focus:ring-2 focus:ring-green-400`}
                  />
                  {errors[id] && (
                    <p className="text-red-500 text-xs mt-1">Please enter a valid number</p>
                  )}
                  <p className="text-gray-400 text-xs mt-1">{helperText}</p>
                </div>
              ))}
            </div>
          </CardContent>
          <CardFooter className="flex flex-col sm:flex-row justify-between items-center mt-6 border-t border-gray-700 pt-6">
            <Button
              variant="outline"
              className="bg-gray-700 text-gray-50 hover:bg-gray-600 px-4 py-2 w-full sm:w-auto"
              onClick={handleReset}
              disabled={isSubmitting}
            >
              Reset
            </Button>
            <AlertDialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
              <AlertDialogTrigger asChild>
                <Button
                  className="bg-green-600 text-gray-50 hover:bg-green-500 px-4 py-2 w-full sm:w-auto"
                  disabled={isSubmitting || !isFormValid}
                >
                  {isSubmitting ? "Submitting..." : "Submit Configuration"}
                </Button>
              </AlertDialogTrigger>
              <AlertDialogContent>
                <AlertDialogHeader>
                  <AlertDialogTitle>Confirm Settings</AlertDialogTitle>
                  <AlertDialogDescription>
                    This action will replace the previous configuration settings. Are you sure you want to proceed?
                  </AlertDialogDescription>
                </AlertDialogHeader>
                <AlertDialogFooter>
                  <AlertDialogCancel>Cancel</AlertDialogCancel>
                  <AlertDialogAction
                    onClick={handleSubmit}
                    className="bg-green-600 hover:bg-green-500 text-gray-50 px-4 py-2"
                  ><Link to={"/vendor-details"}>
                    Confirm
                    </Link>
                  </AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog>
          </CardFooter>
          <div className="text-center text-sm mt-4">
            <span>Want to view previous configurations? </span>
            <Link to="/configuration" className="text-green-400 hover:underline">
              View Previous Settings
            </Link>
          </div>
        </form>
      </Card>
    </div>
  );
}

export default NewConfig;
