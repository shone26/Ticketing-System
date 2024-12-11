import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

function ConfigurationPage() {
  return (
    <div className="bg-gradient-to-br from-gray-800 via-gray-900 to-black text-gray-200 min-h-screen flex items-center justify-center">
      <Card className="w-[400px] bg-gray-900 text-gray-50 rounded-xl shadow-lg p-8">
        <CardHeader className="text-center border-b border-gray-700 pb-6">
          <CardTitle className="text-2xl font-semibold text-green-400">
            Configuration Settings
          </CardTitle>
          <CardDescription className="text-sm text-gray-400 mt-2">
            Choose one of the options below to manage your configurations.
          </CardDescription>
        </CardHeader>
        <CardContent className="mt-6">
          <div className="flex flex-col items-center gap-6">
            <Button className="w-full bg-green-600 hover:bg-green-500 text-gray-50 rounded-md px-6 py-3 text-lg font-medium tracking-wide focus:ring-2 focus:ring-green-400">
              <Link to="/newConfiguration" className="text-gray-50">
                Add New Settings
              </Link>
            </Button>
            <Button className="w-full bg-green-600 hover:bg-green-500 text-gray-50 rounded-md px-6 py-3 text-lg font-medium tracking-wide focus:ring-2 focus:ring-green-400">
              <Link to="/vendor-details" className="text-gray-50">
                View Previous Settings
              </Link>
            </Button>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}

export default ConfigurationPage;
