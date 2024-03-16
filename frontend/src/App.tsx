import { AuthClientTokens } from "@react-keycloak/core";
import { ReactKeycloakProvider, useKeycloak } from "@react-keycloak/web";
import Keycloak from "keycloak-js";
import { useEffect } from "react";
import { AuthenticatedHome } from "./AuthenticatedHome";
import { UnauthenticatedHome } from "./UnauthenticatedHome";
import { useConfig } from "./apis/config";
import { axiosIns } from "./apis/shared-api-config";

function App() {
  const configQuery = useConfig();

  useEffect(() => {
    (window as any).apiBaseUrl = configQuery.data?.apiBaseUrl;
    axiosIns.defaults.baseURL = configQuery.data?.apiBaseUrl;
  }, [configQuery.data]);

  if (configQuery.isLoading) {
    return <div></div>;
  }

  const handleTokenChange = (token: AuthClientTokens) => {
    axiosIns.defaults.headers.common["Authorization"] = `Bearer ${token.token}`;
  };

  return (
    <ReactKeycloakProvider
      authClient={
        new Keycloak({
          clientId: configQuery.data?.clientId as string,
          realm: configQuery.data?.realm as string,
          url: configQuery.data?.authUrl as string,
        })
      }
      onTokens={handleTokenChange}
    >
      <AuthSwitchComponet />
    </ReactKeycloakProvider>
  );
}

export const AuthSwitchComponet = () => {
  const { keycloak } = useKeycloak();

  if (keycloak.authenticated) return <AuthenticatedHome />;
  return <UnauthenticatedHome />;
};

export default App;
