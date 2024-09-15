document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('modal');
    const openModalBtn = document.getElementById('openModalBtn');
    const closeModalBtn = document.getElementById('closeModalBtn');
    const form = document.querySelector('form');
    const linkImagemField = document.getElementById('linkImagem');
    const linkImagemError = document.getElementById('linkImagemError');

    openModalBtn.addEventListener('click', () => {
        modal.style.display = 'block';
    });

    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    // Função para validar o comprimento da URL
    const validateUrlLength = (url) => {
        const maxLength = 200;
        return url.length <= maxLength;
    };

    if (form) {
        form.addEventListener('submit', (event) => {
            const linkImagem = linkImagemField.value;

            if (!validateUrlLength(linkImagem)) {
                // Adiciona uma mensagem de erro e exibe um alerta
                linkImagemError.textContent = 'O link da imagem é muito longo. Por favor, insira um link com no máximo 200 caracteres.';
                linkImagemField.classList.add('error'); // Adiciona uma classe de erro para estilização
                alert('Tamanho do link inválido, adicione novamente por favor!');
                event.preventDefault(); // Impede o envio do formulário
                return; // Mantém o modal aberto
            } else {
                linkImagemError.textContent = ''; // Limpa a mensagem de erro se o comprimento estiver correto
                linkImagemField.classList.remove('error'); // Remove a classe de erro
            }
        });
    }
});
