import { FieldValues, FormState, RegisterOptions } from "react-hook-form";

export type BaseFormTypes<T> = {
  label: string;
  inputProps?: T;
  labelKey: string;
  className?: string;
  registerOptions?: Omit<
    RegisterOptions<FieldValues, string> | undefined,
    "required"
  >;
  commonRegex?: "phone" | "email";
  optional?: boolean;
};

export function loadRegisterOptionsForFormComponents<T>(
  props: BaseFormTypes<T>
): RegisterOptions<FieldValues, string> | undefined {
  const result: RegisterOptions<FieldValues, string> = {};
  if (props.optional) return result;
  else if (props.commonRegex) {
    switch (props.commonRegex) {
      case "email":
        result.pattern = {
          value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
          message: "Invalid Email Address",
        };
        break;
      case "phone":
        result.pattern = {
          value: /^\d{10}$/i,
          message: "Enter Phone Number without Country Code",
        };
    }
  }
  if (!props.optional) {
    result.required = { message: props.label + " is reqired.", value: true };
  }
  return result;
}

export const ErrorComponent = ({
  formState,
  labelKey,
}: {
  formState: FormState<FieldValues>;
  labelKey: string;
}) => {
  return (
    <span className="text-red-500 py-1 text-xs">
      {formState.errors[labelKey]?.message?.toString()}
    </span>
  );
};
