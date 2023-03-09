import Layout from "@/components/Layout/Layout"
import Main from "@/components/Main/Main"
import Head from "next/head"

const Marketplace = () => {

  return (
    <>
      <Head>
        <title>MonoFi Marketplace</title>
        <meta name="description" content="MonoFi Marketplace" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta
          name="keywords"
          content="crypto, monofi, exchange, marketplace, nft"
        />
      </Head>
      <Layout>
        <Main />
      </Layout>
    </>
  )
}

export default Marketplace