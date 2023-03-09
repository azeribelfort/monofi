import { useRouter } from "next/router";
import Center from "./Center";
import Community from "./Community";

const IndividualCompany = () => {

  const router = useRouter();
  const { id } = router.query;
  
  return (
    <div className="indv-page">
        <h1>Overview</h1>
        <div className="indv-container">
            <Center companyId={id} />
            <Community />
        </div>
    </div>
  )
}

export default IndividualCompany