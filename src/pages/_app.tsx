import '@/styles/globals.css';
import '@/styles/sidebar/sidebar.css';
import '@/styles/Main/main.css';
import '@/styles/Main/slider.css';
import '@/styles/Main/spotlight.css';
import 'node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../styles/Landing/Landing.scss'
import '../styles/dashboard/All.scss'
import '../styles/layoutForAuthorization/Signin.scss'
import '../styles/layoutForAuthorization/Signup.scss'
import '../styles/layoutForAuthorization/authorization.scss'
import '../styles/individualCompany/main.scss'
import '../styles/individualCompany/priceInfo.scss'
import '../styles/individualCompany/community.scss'
import '../styles/individualCompany/priceChart.scss'
import '../styles/Payment/Payment.scss'
import '../styles/Payment/Success.scss'
import '../styles/Payment/Failure.scss'
import type { AppProps } from 'next/app';

export default function App({ Component, pageProps }: AppProps) {
  return <Component {...pageProps} />;
}
