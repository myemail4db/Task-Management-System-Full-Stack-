import React, { useState } from "react";

const STATUSES = ["OPEN","IN_PROGRESS","BLOCKED","REVIEW","DONE","CANCELLED"];

export default function UpdateStatus({ taskId, current, onDone, onError }) {
  const [val, setVal] = useState(current);
  const [busy, setBusy] = useState(false);

  async function save() {
    setBusy(true);
    try {
      const res = await fetch(`/api/tasks/${taskId}/status?status=${encodeURIComponent(val)}`, {
        method: "PATCH",
        headers: { "X-Correlation-Id": "react-demo-1" }
      });
      if (!res.ok) {
        const body = await res.json().catch(() => ({}));
        throw new Error(body.message || res.statusText);
      }
      onDone && onDone();
    } catch (e) {
      onError && onError(e.message);
    } finally {
      setBusy(false);
    }
  }

  return (
    <span style={{ marginLeft: 10 }}>
      <select value={val} onChange={e => setVal(e.target.value)} disabled={busy}>
        {STATUSES.map(s => <option key={s} value={s}>{s}</option>)}
      </select>
      <button onClick={save} disabled={busy} style={{ marginLeft: 6 }}>Update</button>
    </span>
  );
}
