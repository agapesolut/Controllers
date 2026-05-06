interface MetricCardProps {
  label: string;
  value: string;
  hint: string;
  tone?: "neutral" | "warning" | "danger" | "success";
}

export function MetricCard({
  label,
  value,
  hint,
  tone = "neutral",
}: MetricCardProps) {
  return (
    <article className={`metric-card ${tone}`}>
      <span>{label}</span>
      <strong>{value}</strong>
      <small>{hint}</small>
    </article>
  );
}
