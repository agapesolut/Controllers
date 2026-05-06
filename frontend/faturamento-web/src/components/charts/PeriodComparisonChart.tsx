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
import { formatCurrency } from "../../utils/formatCurrency";

export function PeriodComparisonChart({
  data,
}: {
  data: ChartPoint[];
}) {
  return (
    <section className="chart-card">
      <div className="chart-header">
        <h3>Comparativo de periodos</h3>
      </div>

      <div className="chart-body">
        <ResponsiveContainer width="100%" height={280}>
          <BarChart data={data}>
            <CartesianGrid strokeDasharray="4 4" stroke="rgba(29, 45, 64, 0.12)" />
            <XAxis dataKey="label" stroke="#4f5d6b" />
            <YAxis stroke="#4f5d6b" />
            <Tooltip formatter={(value: number) => formatCurrency(value)} />
            <Bar dataKey="value" fill="#315f91" radius={[10, 10, 0, 0]} />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </section>
  );
}
