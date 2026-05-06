import { useAuth } from "./useAuth";

export function usePermissions() {
  const { user } = useAuth();

  function hasPermission(permission: string) {
    return user?.permissoes.includes(permission) ?? false;
  }

  return { hasPermission };
}
