package br.com.fiap.pedido.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import br.com.fiap.pedido.dto.ClienteDTO;
import br.com.fiap.pedido.dto.ProdutoDTO;
import br.com.fiap.pedido.dto.PagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fiap.pedido.service.PedidoService;

@Getter
@AllArgsConstructor
public class Pedido {

    private final UUID id;
    private final ClienteDTO cliente;
    private final List<ProdutoDTO> produtos;
    private StatusPedido statusPedido;
    private PagamentoDTO pagamento;

    // Injete o PedidoService no Pedido
    @Autowired
    private PedidoService pedidoService;

    private Pedido(UUID id, ClienteDTO cliente, List<ProdutoDTO> produtos, StatusPedido statusPedido) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.statusPedido = statusPedido;
    }

    public static Pedido criaPedido(UUID id, UUID clienteId, List<UUID> produtoIds) {
        PedidoService pedidoService = new PedidoService();
        ClienteDTO cliente = pedidoService.getClienteById(clienteId);
        List<ProdutoDTO> produtos = pedidoService.getProdutosByIds(produtoIds);

        validateProdutos(produtos);
        return new Pedido(id, cliente, produtos, StatusPedido.AGUARDANDO_PAGAMENTO);

    }

    public static Pedido criaPedido(UUID id, ClienteDTO clienteId, List<ProdutoDTO> produtoIds, StatusPedido status,
            PagamentoDTO pagamentoId) {
        // PedidoService pedidoService = new PedidoService();
        // ClienteDTO cliente = pedidoService.getClienteById(clienteId);
        // List<ProdutoDTO> produtos = pedidoService.getProdutosByIds(produtoIds);
        // PagamentoDTO pagamento = pedidoService.getPagamentoById(pagamentoId);

        return new Pedido(id, clienteId, produtoIds, status);
    }

    private static void validateProdutos(List<ProdutoDTO> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Produtos não pode ser nulo ou vazio.");
        }
    }

    private static void validatePagamento(PagamentoDTO pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
        }
    }

    public void registaPagamento(PagamentoDTO pagamento) {
        validatePagamento(pagamento);
        this.pagamento = pagamento;
    }

    public void pagamentoRecebido() {
        this.statusPedido = StatusPedido.EM_PREPARACAO;
    }

    public void preparoFinalizado() {
        this.statusPedido = StatusPedido.PREPARO_FINALIZADO;
    }

    public void entregue() {
        this.statusPedido = StatusPedido.ENTREGUE;
    }

    public BigDecimal valorTotalPedido() {
        return this.produtos.stream()
                .map(ProdutoDTO::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
