import { useKeycloak } from "@react-keycloak/web";
import { useEffect, useState } from "react";
import { BlockIcon } from "../icon/block-icon";
import { Button } from "./button";
import { NavbarAvatar } from "./navbar-avatar";

export const Navbar = () => {
  const { keycloak, initialized } = useKeycloak();

  const [label, setLabel] = useState<string>();

  const loadUserInfo = async () => {
    const userInfo: any = await keycloak?.loadUserInfo();
    setLabel(userInfo.name[0] as string);
  };

  const handleSignOut = () => {
    keycloak.logout();
  };

  useEffect(() => {
    initialized && loadUserInfo();
  }, [keycloak]);

  return (
    <div className="flex px-4 py-4 items-center justify-between gap-3 flex-row border-b border-gray-50/30">
      <div className="flex flex-row items-center gap-1">
        <BlockIcon className="h-8 w-8" />
        <div className="font-semibold text-white text-xl">OneAuth</div>
      </div>
      <div className="flex flex-row gap-2">
        {initialized && label && <NavbarAvatar label={label} />}
        <Button
          label="SignOut"
          category="secondary"
          size="xs"
          inputProps={{ onClick: handleSignOut }}
        />
      </div>
    </div>
  );
};
