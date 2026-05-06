import type { ReactNode } from "react";

interface PageContainerProps {
  title: string;
  subtitle: string;
  children: ReactNode;
  actions?: ReactNode;
}

export function PageContainer({
  title,
  subtitle,
  children,
  actions,
}: PageContainerProps) {
  return (
    <section className="page-container">
      <div className="page-header">
        <div>
          <p className="page-eyebrow">MVP em validacao</p>
          <h2>{title}</h2>
          <p>{subtitle}</p>
        </div>

        {actions ? <div className="page-actions">{actions}</div> : null}
      </div>

      {children}
    </section>
  );
}
