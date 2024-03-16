import { GenericSvgProps } from "../utils/misc-types";

export function DeleteIcon(props: GenericSvgProps) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="200"
      height="200"
      fill="currentColor"
      stroke="currentColor"
      strokeWidth="0"
      viewBox="0 0 24 24"
      {...props}
    >
      <path fill="none" stroke="none" d="M0 0h24v24H0z"></path>
      <path
        stroke="none"
        d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"
      ></path>
    </svg>
  );
}
