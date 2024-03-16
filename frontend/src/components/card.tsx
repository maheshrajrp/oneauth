import { useDeleteCustomersAPI } from "../apis/customers";
import { GetCustomerAPIType } from "../apis/types/customer-api-types";
import { DeleteIcon } from "../icon/delete-icon";

export type CustomerCardProps = GetCustomerAPIType;

export const Card = (props: CustomerCardProps) => {
  const deleteCustomerMutation = useDeleteCustomersAPI();

  const handleDelete = () => deleteCustomerMutation.mutate(props.id);

  return (
    <div className="border border-gray-50/20 hover:border-white transition-all cursor-pointer px-4 py-4 text-white rounded-md">
      <div className="flex flex-col gap-2 relative">
        <span className="text-2xl font-semibold">
          {props.firstName + " " + props.lastName}
        </span>
        <div className="text-lightFont text-sm flex flex-col">
          <span>{props.email}</span>
          <span>{props.phone}</span>
          <span>{props.type}</span>
        </div>
        <div
          className="absolute bottom-0 right-0 text-lightFont hover:text-white"
          onClick={handleDelete}
        >
          <DeleteIcon className="h-4 w-4 " />
        </div>
      </div>
    </div>
  );
};
