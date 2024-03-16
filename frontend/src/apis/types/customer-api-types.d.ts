export type CreateCustomerAPIType = {
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    type: string;
  };

export type GetCustomerAPIType = {
  id: string
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  type: string;
};

export type GetAllCustomerAPIType = {value: GetCustomerAPIType[]};