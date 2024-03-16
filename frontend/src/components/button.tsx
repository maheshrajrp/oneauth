import { ButtonHTMLAttributes, DetailedHTMLProps } from "react";

type ButtonProps = {
  label: string;
  size?: "xs" | "md";
  category?: "primary" | "secondary";
  inputProps?: DetailedHTMLProps<
    ButtonHTMLAttributes<HTMLButtonElement>,
    HTMLButtonElement
  >;
};

function getPaddingSize(size: ButtonProps["size"]) {
  switch (size) {
    case "xs":
      return "px-2 text-xs rounded-[10px]";
    case "md":
    default:
      return "px-6 py-2 rounded-[15px]";
  }
}

function getCategoryClass(props: ButtonProps) {
  switch (props.category) {
    case "secondary":
      return (
        (props.inputProps?.disabled
          ? "bg-gray-300"
          : " hover:bg-gray-200 hover:text-black") +
        " text-white border-2 border-white "
      );
    default:
    case "primary":
      return (
        (props.inputProps?.disabled
          ? "bg-gray-300"
          : "bg-gray-100 hover:bg-gray-200") + " text-darkFont "
      );
  }
}

export const Button = (props: ButtonProps) => {
  return (
    <button
      className={`font-semibold  border-black rounded-md  ${getPaddingSize(
        props.size
      )} ${getCategoryClass(props)}`}
      {...props.inputProps}
    >
      {props.label}
    </button>
  );
};
