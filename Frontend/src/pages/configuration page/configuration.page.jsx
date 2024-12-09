import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Label } from "@radix-ui/react-label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

function ConfigurationPage() {
  return (
    <div>
  <div className="dark:bg-gray-900 dark:text-white bg-white text-black min-h-screen flex items-center justify-center">
    <Card className="w-[350px]">
      <CardHeader className="text-center">
        <CardTitle className="text-lg">Configuration Page</CardTitle>
        <CardDescription>
          Welcome to Configuration Page. Select from the following
        </CardDescription>
      </CardHeader>
      <CardContent>
        
          <div className="flex flex-col items-center gap-4">
            <Button className="dark:bg-gray-700 dark:text-white w-full">
            <Link to={"/newConfiguration"}>
            Add New Configuration Settings
          </Link>
            </Button>
            <Button className="dark:bg-gray-700 dark:text-white w-full">
            <Link to={"/vendor-details"}>
              Add Previous Configuration Settings
              </Link>
            </Button>
          </div>
        
      </CardContent>
    </Card>
  </div>
</div>

  );
}
export default ConfigurationPage;
