import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Label } from "@radix-ui/react-label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"

import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Link } from "react-router-dom";



function SignUpPage() {
    return(
      <div>
        <div className="dark:bg-gray-900 dark:text-white bg-white text-black min-h-screen flex items-center justify-center">
        <Card className="w-[350px]">
          <CardHeader>
            <CardTitle>Create an account</CardTitle>
            <CardDescription>Create your account and grab your tickets in one click!</CardDescription>
          </CardHeader>
          <CardContent>
            <form>
              <div className="grid w-full items-center gap-4">
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Full Name</Label>
                  <Input
                    id="name"
                    placeholder="Enter your full name"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Username</Label>
                  <Input
                    id="username"
                    placeholder="Enter your username"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Enter a password</Label>
                  <Input
                    id="password"
                    placeholder="Enter a password"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Email Address</Label>
                  <Input
                    id="email"
                    placeholder="Enter your password"
                    className="dark:bg-gray-800 dark:text-white"
                  />
                </div>
                
              </div>
            </form>
          </CardContent>
          <CardFooter className="flex justify-between pb-1">
            <Button variant="outline" className="dark:bg-gray-800 dark:text-white">
              Cancel
            </Button>
            <Button className="dark:bg-gray-700 dark:text-white">Sign up</Button>
            
          </CardFooter>
          <div className="mt-4 text-center text-sm pb-6">
          Already have an account?{" "}
          <Link to={"/sign-in"}>
            Sign in
          </Link>
        </div>
        </Card>
      </div>
    </div>
    );
  }
  
  export default SignUpPage;
  