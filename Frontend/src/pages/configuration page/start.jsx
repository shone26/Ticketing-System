import React, { useState } from 'react';

function StartSystemButton() {
  const [status, setStatus] = useState('');

  const startSystem = async () => {
    try {
      const response = await fetch('http://localhost:8080/startsystem', {
        method: 'POST',
      });

      if (response.ok) {
        const data = await response.json();
        setStatus(data.message || 'System started successfully!');
      } else {
        setStatus('Failed to start system');
      }
    } catch (error) {
      setStatus('Error: ' + error.message);
    }
  };

  return (
    <div>
      <button onClick={startSystem}>Start System</button>
      {status && <p>{status}</p>}
    </div>
  );
}

export default StartSystemButton;
