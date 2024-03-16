import { useMutation, useQuery } from "@tanstack/react-query";
import { queryClient } from "../main";
import { axiosIns } from "./shared-api-config";
import { CreateCustomerAPIType, GetAllCustomerAPIType } from "./types/customer-api-types";

export const useCreateCustomerAPI = () => {
  return useMutation({
    mutationFn: (data: CreateCustomerAPIType) => {
      return axiosIns.post("/v1/customers", data);
    },
    onSuccess: async () => {
      queryClient.invalidateQueries({queryKey: ['all-customers']})
    }
  });
};

export const useDeleteCustomersAPI = () => {
  return useMutation({
    mutationFn: (id: string) => {
      return axiosIns.delete("/v1/customers/"+id);
    },
    onSuccess: async () => {
      queryClient.invalidateQueries({queryKey: ['all-customers']})
    }
  });
};

export const useGetAllCustomersAPI = () => {
  return useQuery({
    queryKey: ['all-customers'],
    queryFn: async () => {
      return (await axiosIns.get<GetAllCustomerAPIType>("/v1/customers")).data.value;
    },
    initialData: [] as GetAllCustomerAPIType['value'],
  });
};