import IndividualCompany from "@/components/Company/IndividualCompany"
import Layout from "@/components/Layout/Layout"
import Head from "next/head"

const CompanyPage = () => {
  return (
    <>
    <Head>
        <title>MonoFi | Company</title>
        <meta name="description" content="MonoFi Marketplace" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta
        name="keywords"
        content="crypto, monofi, exchange, marketplace, nft"
        />
    </Head>
    <Layout>
        <IndividualCompany />
    </Layout>
    </>
  )
}

export default CompanyPage