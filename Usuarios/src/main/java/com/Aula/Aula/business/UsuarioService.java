package com.Aula.Aula.business;

import com.Aula.Aula.infraestructure.entitys.Usuario;
import com.Aula.Aula.infraestructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

// Marca essa classe como Service (camada de negócio)
@Service
public class UsuarioService {

    // Repository responsavel por acessar o banco
    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência via construtor (boa pratica)
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Salva um novo usuario no banco
    public void salvarUsuario(Usuario usuario) {
        // saveAndFlush salva e executa o SQL imediatamente
        usuarioRepository.saveAndFlush(usuario);
    }

    // Busca um usuario pelo email
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                // Se não encontrar, lança exceção
                .orElseThrow(() -> new RuntimeException("Email não encontrado!"));
    }

    // Deleta um usuario pelo email
    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    // Atualiza um usuario pelo ID
    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {

        // Busca o usuario existente no banco
        Usuario usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));

        // Cria um novo objeto Usuario usando Builder
        // Atualização parcial: só altera o que não for null
        Usuario usuarioAtualizado = Usuario.builder()
                // Se veio email novo, usa ele : senão, mantem o antigo
                .email(usuario.getEmail() != null ?
                        usuario.getEmail() : usuarioEntity.getEmail())

                // Se veio nome novo, usa ele : senão, mantem o antigo
                .nome(usuario.getNome() != null ?
                        usuario.getNome() : usuarioEntity.getNome())

                // Mantem o mesmo ID para o JPA entender como UPDATE
                .id(usuarioEntity.getId())
                .build();

        // Salva a atualização no banco
        usuarioRepository.saveAndFlush(usuarioAtualizado);
    }
}
