import React from 'react';
import BalanceCard from './components/BalanceCard';
import SideBarItems from './components/SideBarItems';

const Sidebar = () => {
  return (
    <div className="d-flex flex-column">
      <BalanceCard />
      <SideBarItems />
    </div>
  );
};

export default Sidebar;
