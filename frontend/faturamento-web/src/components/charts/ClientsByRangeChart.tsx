import {
  Bar,
  BarChart,
  CartesianGrid,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";
import type { ChartPoint } from "../../types/Dashboard";

export function ClientsByRangeChart({
  data,
}: {
  data: ChartPoint[];
}) {
  return (
    <section className="chart-card">
      <div className="chart-header">
        <h3>Clientes por faixa</h3>
      </div>

      <div className="chart-body">
        <ResponsiveContainer width="100%" height={280}>
          <BarChart data={data}>
            <CartesianGrid strokeDasharray="4 4" stroke="rgba(29, 45, 64, 0.12)" />
            <XAxis dataKey="label" stroke="#4f5d6b" />
            <YAxis allowDecimals={false} stroke="#4f5d6b" />
            <Tooltip />
            <Bar dataKey="value" fill="#1a6f72" radius={[10, 10, 0, 0]} />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </section>
  );
}
