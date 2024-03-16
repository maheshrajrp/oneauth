import { DetailedHTMLProps, InputHTMLAttributes } from "react";
import { useFormContext } from "react-hook-form";
import {
  BaseFormTypes,
  ErrorComponent,
  loadRegisterOptionsForFormComponents,
} from "../base-form-utils";

export type InputTextProps = BaseFormTypes<
  DetailedHTMLProps<InputHTMLAttributes<HTMLInputElement>, HTMLInputElement>
>;

export const InputText = (props: InputTextProps) => {
  const { register, formState } = useFormContext();

  return (
    <div
      className={`flex flex-col py-[0.1rem] ${
        props.className ? props.className : ""
      }`}
    >
      <label className="flex flex-col ">
        <span className="text-fontSecondaryColor pb-1 text-sm">
          {props.label}
        </span>
        <input
          className="border-[1px] border-inputBorderColor rounded-sm py-[5px] px-2"
          {...register(props.labelKey, {
            ...props.registerOptions,
            ...loadRegisterOptionsForFormComponents(props),
          })}
          {...props.inputProps}
        ></input>
      </label>
      <ErrorComponent formState={formState} labelKey={props.labelKey} />
    </div>
  );
};
