const API_URL = "http://localhost:8080/produtos";

function mostrarMensagem(texto, erro = false) {
    const msg = document.getElementById("mensagem");
    msg.textContent = texto;
    msg.style.color = erro ? "red" : "green";
}

async function salvarProduto() {
    const produto = {
        nome: document.getElementById("nome").value,
        tipo: document.getElementById("tipo").value,
        preco: document.getElementById("preco").value
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(produto)
        });

        if (!response.ok) {
            const erro = await response.text();
            throw new Error(erro);
        }

        mostrarMensagem("Produto salvo com sucesso!");
    } catch (e) {
        mostrarMensagem(e.message, true);
    }
}

async function atualizarProduto() {
    const id = document.getElementById("id").value;

    const produto = {
        nome: document.getElementById("nome").value,
        tipo: document.getElementById("tipo").value,
        preco: document.getElementById("preco").value
    };

    try {
        const response = await fetch(`${API_URL}?id=${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(produto)
        });

        if (!response.ok) {
            const erro = await response.text();
            throw new Error(erro);
        }

        mostrarMensagem("Produto atualizado com sucesso!");
    } catch (e) {
        mostrarMensagem(e.message, true);
    }
}

async function buscarProduto() {
    const nome = document.getElementById("buscarNome").value;

    try {
        const response = await fetch(`${API_URL}?nome=${nome}`);

        if (!response.ok) {
            const erro = await response.text();
            throw new Error(erro);
        }

        const produto = await response.json();
        document.getElementById("resultado").textContent =
            JSON.stringify(produto, null, 2);
    } catch (e) {
        document.getElementById("resultado").textContent = e.message;
    }
}

async function deletarProduto() {
    const nome = document.getElementById("deletarNome").value;

    try {
        const response = await fetch(`${API_URL}?nome=${nome}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            const erro = await response.text();
            throw new Error(erro);
        }

        mostrarMensagem("Produto deletado com sucesso!");
    } catch (e) {
        mostrarMensagem(e.message, true);
    }
}
