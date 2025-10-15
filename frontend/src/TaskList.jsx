import React, { useEffect, useState } from "react";
import UpdateStatus from "./UpdateStatus";

export default function TaskList() {
  const [q, setQ] = useState("");
  const [page, setPage] = useState(0);
  const [size] = useState(10);
  const [data, setData] = useState({ content: [], totalElements: 0, totalPages: 0 });
  const [loading, setLoading] = useState(false);
  const [err, setErr] = useState("");

  async function load(p = page) {
    setLoading(true); setErr("");
    try {
      const url = q
        ? `/api/tasks?q=${encodeURIComponent(q)}&page=${p}&size=${size}`
        : `/api/tasks?page=${p}&size=${size}`;
      const res = await fetch(url, { headers: { "X-Correlation-Id": "react-demo-1" } });
      if (!res.ok) {
        const body = await res.json().catch(() => ({}));
        throw new Error(body.message || res.statusText);
      }
      const d = await res.json();
      setData(d);
      setPage(p);
    } catch (e) {
      setErr(e.message);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(0); /* initial */ }, []); // eslint-disable-line

  return (
    <div style={{ padding: 16, fontFamily: "system-ui" }}>
      <h2>Tasks (React)</h2>

      <div style={{ display: "flex", gap: 8, marginBottom: 12 }}>
        <input value={q} onChange={e => setQ(e.target.value)} placeholder="search title..." />
        <button onClick={() => load(0)}>Search</button>
      </div>

      {err && <div style={{ color: "#b00", marginBottom: 8 }}>{err}</div>}
      {loading && <div>Loading...</div>}

      <ul>
        {(data.content || []).map(t => (
          <li key={t.taskId} style={{ marginBottom: 6 }}>
            <strong>#{t.taskId}</strong> {t.title}
            <span style={tag}>{t.status}</span>
            <span style={tag}>{t.priority}</span>
            <UpdateStatus
              taskId={t.taskId}
              current={t.status}
              onDone={() => load(page)}
              onError={(m) => setErr(m)}
            />
          </li>
        ))}
      </ul>

      <div style={{ marginTop: 8, display: "flex", gap: 8 }}>
        <button onClick={() => load(Math.max(0, page - 1))} disabled={page === 0}>Prev</button>
        <span>Page {page + 1} / {Math.max(1, data.totalPages || 1)}</span>
        <button onClick={() => load(page + 1)} disabled={data.totalPages && page + 1 >= data.totalPages}>Next</button>
      </div>
    </div>
  );
}

const tag = { padding: "2px 6px", border: "1px solid #ddd", borderRadius: 10, marginLeft: 8, fontSize: 12 };
