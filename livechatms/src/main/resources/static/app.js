const stompClient = new StompJs.Client({
    brokerURL: 'ws://' + window.location.host + '/buildrun-livechat-websocket'
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);

    stompClient.subscribe('/topics/live-chat', (message) => {
        const data = JSON.parse(message.body);
        updateLiveChat(data.content, data.sender);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('WebSocket error', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker error: ' + frame.headers['message']);
    console.error(frame.body);
};

function connect() {
    stompClient.activate();
}

function sendMessage() {
    stompClient.publish({
        destination: "/app/new-message",
        body: JSON.stringify({ message: document.getElementById("message").value })
    });
}

function updateLiveChat(message, sender) {
    const li = document.createElement("li");

    li.innerHTML = `
        <div class="d-flex ${sender === username ? 'justify-content-end' : 'justify-content-start'} mb-2">
            <div class="p-2 rounded ${sender === username ? 'bg-primary text-white' : 'bg-light'}" 
                 style="max-width: 70%">
                <strong>${sender}</strong><br>
                ${message}
            </div>
        </div>
    `;

    document.getElementById("livechat").appendChild(li);

    const chatBox = document.getElementById("chat-box");
    chatBox.scrollTop = chatBox.scrollHeight;
}

// Form submit
document.getElementById("messageForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const input = document.getElementById("message");
    const content = input.value.trim();

    if (content) {
        sendMessage(content);
        input.value = "";
    }
});

// Auto connect when page loads
connect();