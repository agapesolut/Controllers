import {
  Area,
  AreaChart,
  CartesianGrid,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";
import type { ChartPoint } from "../../types/Dashboard";
import { formatCurrency } from "../../utils/formatCurrency";

export function RevenueChart({
  data,
  title,
}: {
  data: ChartPoint[];
  title: string;
}) {
  return (
    <section className="chart-card">
      <div className="chart-header">
        <h3>{title}</h3>
      </div>

      <div className="chart-body">
        <ResponsiveContainer width="100%" height={280}>
          <AreaChart data={data}>
            <defs>
              <linearGradient id="revenueFill" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#c96b1e" stopOpacity={0.38} />
                <stop offset="95%" stopColor="#c96b1e" stopOpacity={0.02} />
              </linearGradient>
            </defs>
            <CartesianGrid strokeDasharray="4 4" stroke="rgba(29, 45, 64, 0.12)" />
            <XAxis dataKey="label" stroke="#4f5d6b" />
            <YAxis stroke="#4f5d6b" />
            <Tooltip formatter={(value: number) => formatCurrency(value)} />
            <Area
              type="monotone"
              dataKey="value"
              stroke="#b2571b"
              strokeWidth={3}
              fill="url(#revenueFill)"
            />
          </AreaChart>
        </ResponsiveContainer>
      </div>
    </section>
  );
}
