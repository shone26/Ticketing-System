import React, { useState } from "react";


function TestPage(){
    const [numCustomers, setNumCustomers] = useState(0);
  const [showCustomerForm, setShowCustomerForm] = useState(false);
  const [customers, setCustomers] = useState([]);

  // Handles the input for the number of customers
  const handleCustomerCountChange = (e) => {
    setNumCustomers(Number(e.target.value));
  };

  // When the Next button is clicked, show the customer input forms
  const handleNextClick = () => {
    if (numCustomers > 0) {
      // Create an array for customer forms with empty values
      const customerArray = Array.from({ length: numCustomers }, () => ({
        firstName: "",
        lastName: "",
        isVip: "no",
      }));
      setCustomers(customerArray);
      setShowCustomerForm(true); // Show the customer forms
    } else {
      alert("Please enter a valid number of customers.");
    }
  };

  // Handles form input changes for each customer
  const handleInputChange = (index, field, value) => {
    const updatedCustomers = [...customers];
    updatedCustomers[index][field] = value;
    setCustomers(updatedCustomers);
  };

  return (
    <div className="container">
      <h1>Customer Form</h1>

      {/* Step 1: Ask how many customers to add */}
      {!showCustomerForm ? (
        <div>
          <label>How many customers do you want to add?</label>
          <input
            type="number"
            min="1"
            value={numCustomers}
            onChange={handleCustomerCountChange}
          />
          <button onClick={handleNextClick}>Next</button>
        </div>
      ) : (
        <div>
          {/* Step 2: Display input forms for each customer */}
          {customers.map((customer, index) => (
            <div key={index} className="customer-card">
              <h3>Customer {index + 1}</h3>
              <div>
                <label>First Name:</label>
                <input
                  type="text"
                  value={customer.firstName}
                  onChange={(e) =>
                    handleInputChange(index, "firstName", e.target.value)
                  }
                />
              </div>
              <div>
                <label>Last Name:</label>
                <input
                  type="text"
                  value={customer.lastName}
                  onChange={(e) =>
                    handleInputChange(index, "lastName", e.target.value)
                  }
                />
              </div>
              <div>
                <label>Is VIP:</label>
                <label>
                  <input
                    type="radio"
                    name={`vip-${index}`}
                    value="yes"
                    checked={customer.isVip === "yes"}
                    onChange={() =>
                      handleInputChange(index, "isVip", "yes")
                    }
                  />
                  Yes
                </label>
                <label>
                  <input
                    type="radio"
                    name={`vip-${index}`}
                    value="no"
                    checked={customer.isVip === "no"}
                    onChange={() =>
                      handleInputChange(index, "isVip", "no")
                    }
                  />
                  No
                </label>
              </div>
            </div>
          ))}
          {/* You can add a submit button here if needed */}
        </div>
      )}
    </div>
  );
};

export default TestPage;