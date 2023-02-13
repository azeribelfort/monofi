import DashboardIcon from '../../../assets/SideBar/DashboardIcon.svg';
import WalletIcon from '../../../assets/SideBar/WalletIcon.svg';
import FiltersIcon from '../../../assets/SideBar/FiltersIcon.svg';
import TradeIcon from '../../../assets/SideBar/TradeIcon.svg';
import SettingsIcon from '../../../assets/SideBar/SettingsIcon.svg';
import LogoutIcon from '../../../assets/SideBar/LogoutIcon.svg';
import HelpIcon from '../../../assets/SideBar/HelpIcon.svg';

const SideBarItems = () => {
  return (
    <div className="my-5">
      <nav>
        <ul className="p-0">
          <li>
            <DashboardIcon />
            <a className="mx-4">Dashboard</a>
          </li>
          <li>
            <WalletIcon />
            <a className="mx-4">My wallet</a>
          </li>
          <li>
            <FiltersIcon />
            <a className="mx-4">Filters</a>
          </li>
          <li>
            <TradeIcon />
            <a className="mx-4">Trade</a>
          </li>
          <li>
            <SettingsIcon />
            <a className="mx-4">Settings</a>
          </li>
          <li>
            <HelpIcon />
            <a className="mx-4">Help</a>
          </li>
          <li>
            <LogoutIcon />
            <a className="mx-4">Log Out</a>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default SideBarItems;
