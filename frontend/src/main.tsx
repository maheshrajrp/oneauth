import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import "primeicons/primeicons.css";
import { PrimeReactProvider } from "primereact/api";
import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";

export const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById("root")!).render(
  <QueryClientProvider client={queryClient}>
    <PrimeReactProvider>
      <App />
    </PrimeReactProvider>
  </QueryClientProvider>
);
