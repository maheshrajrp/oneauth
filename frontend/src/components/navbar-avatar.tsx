export type NavbarAvatarProps = {
  label: string;
};

export const NavbarAvatar = (props: NavbarAvatarProps) => {
  return (
    <div className="gradient text-white font-semibold p-2 px-4 rounded-full">
      {props.label.charAt(0).toUpperCase()}
    </div>
  );
};
