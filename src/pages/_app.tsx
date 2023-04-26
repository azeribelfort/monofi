import '@/styles/globals.css';
import '@/styles/sidebar/sidebar.css';
import '@/styles/Layout/layout.css';
import '@/styles/Layout/slider.css';
import '@/styles/Layout/spotlight.css';
import 'node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../styles/Landing/Landing.scss'
import '../styles/dashboard/All.scss'
import '../styles/layoutForAuthorization/Signin.scss'
import '../styles/layoutForAuthorization/Signup.scss'
import '../styles/layoutForAuthorization/authorization.scss'
import '../styles/Payment/Payment.scss'
import '../styles/Payment/Success.scss'
import '../styles/Payment/Failure.scss'
import type { AppProps } from 'next/app';

export default function App({ Component, pageProps }: AppProps) {
  return <Component {...pageProps} />;
}
