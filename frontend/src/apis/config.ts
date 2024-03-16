import { useQuery } from "@tanstack/react-query";

export const useConfig = () => {
    return useQuery({
      queryKey: ['all-config'],
      queryFn: async () => {
        return fetch("/config.json").then((res) => res.json() as Promise<APIConfigType>);
      },
      refetchInterval: false,
      refetchOnMount: false,
      refetchOnWindowFocus: false,
    });
  };