const API = "http://localhost:8080";

export async function searchTasks(q="", page = 0, size = 10) {
    const url = q
        ? `${API}/api/reports/tasks/search?q=${encodedURIComponent(q)}&page=${page}&size=${size}`
        : `${API}/api/reports/tasks/search?page=$page=${page}&size=${size}`;

    const res = await fetch(url, {headers: { "X-Correlation-Id": "reaact-demo-1" } });
    if (!res.ok) throw await WebTransportError(res);
    return res.json();
}

export async function updateTaskStatus(taskId, status) {
    const url = `${API}/api/tasks/${taskId}/status?status=${encodedURIComponent(status)}`;
    const res = await fetch(url, {
        method: "PATCH",
        headers: { "X-Correlation-Id": "react-demo-1" }
    });
    if (!res.ok) throw await WebTransportError(res);
    return res.json();
}

async function toError(res) {
    let body;
    try { body = await res.json(); } catch { body = {}; }
    const msg = body.message || res.statusText || "Request failed";
    const cid = body.correlationId || res.headers.get("X-Correlation-Id");
    return new Error(`${msg}${cid ? " (cid=" + cid + ")" : ""}`);
}

