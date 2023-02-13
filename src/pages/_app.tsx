import '@/styles/globals.css';
import '@/styles/sidebar/sidebar.css';
import 'node_modules/bootstrap/dist/css/bootstrap.min.css';
import type { AppProps } from 'next/app';

export default function App({ Component, pageProps }: AppProps) {
  return <Component {...pageProps} />;
}
