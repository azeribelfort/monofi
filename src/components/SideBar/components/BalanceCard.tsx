import React from 'react';

const BalanceCard = () => {
  return (
    <div className="balance-card mt-2">
      <div className="text-start my-3 ms-4">
        <h4 className="fw-light">Balance 💰</h4>
      </div>
      <div className="text-center">
        <h2>$1982.37</h2>
        <div>
          <svg
            width="10"
            height="8"
            viewBox="0 0 10 8"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path d="M5 0L9.33013 7.5H0.669873L5 0Z" fill="#5CFF9C" />
          </svg>
          <span className="fw-light opacity-25">15% This week</span>
        </div>
      </div>
    </div>
  );
};

export default BalanceCard;
