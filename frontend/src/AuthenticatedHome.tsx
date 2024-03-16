import { Sidebar } from "primereact/sidebar";
import { useState } from "react";
import { FormProvider, useForm } from "react-hook-form";
import { useCreateCustomerAPI, useGetAllCustomersAPI } from "./apis/customers";
import { CreateCustomerAPIType } from "./apis/types/customer-api-types";
import { Button } from "./components/button";
import { Card } from "./components/card";
import { InputText } from "./components/input/input-text";
import { Navbar } from "./components/navbar";

export const AuthenticatedHome = () => {
  const [visible, setVisible] = useState(false);

  const methods = useForm<CreateCustomerAPIType>();
  const handleClick = () => setVisible(true);
  const mutation = useCreateCustomerAPI();

  const getCustomerQuery = useGetAllCustomersAPI();

  const onSuccess = () => {
    console.log("SUCCESS");
  };
  const onSubmit = () => {
    mutation.mutate(methods.getValues(), { onSuccess });
    handleSidebarHide();
  };

  const handleSidebarHide = () => {
    methods.reset();
    setVisible(false);
  };

  return (
    <div className="flex flex-col">
      <Navbar />
      <div className="flex-1 px-16 py-16">
        <div className="flex flex-row pb-2">
          <h4 className="text-xl pb-4 flex-1">Overview</h4>
          <div>
            <Button
              label="Add"
              size="md"
              inputProps={{ onClick: handleClick }}
            />
          </div>
        </div>
        <div className="md:grid md:grid-cols-3 gap-4">
          {getCustomerQuery.data.length === 0 && (
            <span className="text-xs">
              No items to show. Add items to start listing.
            </span>
          )}
          {getCustomerQuery.data?.map((ele) => (
            <Card {...ele} />
          ))}
        </div>
      </div>

      <Sidebar
        visible={visible}
        onHide={handleSidebarHide}
        position="right"
        header={
          <h2 className="text-2xl font-semibold pt-4">Create Customer</h2>
        }
      >
        <form
          id="contact-us-form"
          className="flex-1"
          onSubmit={methods.handleSubmit(onSubmit)}
        >
          <FormProvider {...methods}>
            <div className="py-4 pr-[15%] mt-auto mb-auto">
              <InputText label="First Name" labelKey="firstName" />
              <InputText label="Last Name" labelKey="lastName" />
              <InputText label="Email" labelKey="email" commonRegex="email" />
              <InputText label="Phone" labelKey="phone" commonRegex="phone" />
              <InputText label="Type" labelKey="type" />
            </div>
          </FormProvider>
        </form>
        <div className="flex flex-row justify-end pr-[15%]">
          <Button
            label="Submit"
            inputProps={{
              type: "submit",
              form: "contact-us-form",
              disabled: mutation.isPending,
            }}
          />
        </div>
      </Sidebar>
    </div>
  );
};
