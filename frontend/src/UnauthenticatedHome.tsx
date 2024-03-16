import { useKeycloak } from "@react-keycloak/web";
import { Button } from "./components/button";
import { BlockIcon } from "./icon/block-icon";
import { WarningIcon } from "./icon/warning-icon";

export const UnauthenticatedHome = () => {
  const { keycloak, initialized } = useKeycloak();

  const handleSignInClick = () => keycloak.login();

  return (
    <div className="flex h-full py-5 px-20">
      <div className="flex flex-col w-full gap-10">
        <div className="flex gap-6 flex-col justify-center items-center h-full">
          <div className="flex justify-center flex-col items-center">
            <BlockIcon className="h-24 w-24" />
            <span>Unified Identity and Access Management</span>
            <h2 className="text-6xl font-bold text-white">OneAuth</h2>
          </div>
          {initialized && (
            <Button
              label="Sign In"
              size="md"
              inputProps={{ onClick: handleSignInClick }}
            />
          )}
        </div>
      </div>
      <div className="absolute right-0 bottom-0">
        <div
          className="p-2 m-2 text-orange-300 shadow-white rounded-md cursor-pointer"
          title="Force API Calls"
        >
          <WarningIcon />
        </div>
      </div>
    </div>
  );
};
