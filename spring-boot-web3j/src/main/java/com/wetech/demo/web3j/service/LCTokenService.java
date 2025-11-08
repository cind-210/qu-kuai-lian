package com.wetech.demo.web3j.service;

import com.wetech.demo.web3j.contracts.lctoken.LCToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class LCTokenService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;

    private LCToken contract;

    /**
     * -- GETTER --
     *  Get the address of the currently loaded contract
     *
     * @return the contract address
     */
    @Getter
    private String contractAddress;

    /**
     * Deploy the LCToken contract to the blockchain
     * @return the address of the deployed contract
     */
    public CompletableFuture<String> deployContract() {
        log.info("Deploying LCToken contract...");
        return LCToken.deploy(web3j, credentials, gasProvider)
                .sendAsync()
                .thenApply(contract -> {
                    this.contract = contract;
                    this.contractAddress = contract.getContractAddress();
                    log.info("LCToken contract deployed to: " + contractAddress);
                    return contractAddress;
                });
    }

    /**
     * Load an existing contract from the blockchain
     * @param contractAddress the address of the contract to load
     */
    public void loadContract(String contractAddress) {
        log.info("Loading LCToken contract from address: " + contractAddress);
        this.contract = LCToken.load(contractAddress, web3j, credentials, gasProvider);
        this.contractAddress = contractAddress;
    }

    /**
     * Mint new tokens
     * @param toAddress the address to receive the minted tokens
     * @param amount the amount of tokens to mint
     * @return the transaction receipt
     */
    public CompletableFuture<TransactionReceipt> mint(String toAddress, BigInteger amount) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Minting " + amount.toString() + " tokens to address: " + toAddress);
        return contract.mint(toAddress, amount).sendAsync();
    }

    /**
     * Transfer tokens to another address
     * @param toAddress the recipient address
     * @param amount the amount of tokens to transfer
     * @return the transaction receipt
     */
    public CompletableFuture<TransactionReceipt> transfer(String toAddress, BigInteger amount) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Transferring " + amount.toString() + " tokens to address: " + toAddress);
        return contract.transfer(toAddress, amount).sendAsync();
    }

    /**
     * Get the balance of an address
     * @param address the address to check balance for
     * @return the balance amount
     */
    public CompletableFuture<BigInteger> balanceOf(String address) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Getting balance for address: " + address);
        return contract.balanceOf(address).sendAsync();
    }

    /**
     * Approve an address to spend tokens on behalf of the caller
     * @param spenderAddress the address to approve
     * @param amount the amount to approve
     * @return the transaction receipt
     */
    public CompletableFuture<TransactionReceipt> approve(String spenderAddress, BigInteger amount) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Approving " + amount.toString() + " tokens for spender: " + spenderAddress);
        return contract.approve(spenderAddress, amount).sendAsync();
    }


    /**
     * 获取当前服务使用的钱包地址
     */
    public String getServiceAddress() {
        return credentials.getAddress().toLowerCase();
    }

    /**
     * Transfer tokens from an approved address
     * @param fromAddress the address to transfer from
     * @param toAddress the address to transfer to
     * @param amount the amount to transfer
     * @return the transaction receipt
     */
    public CompletableFuture<TransactionReceipt> transferFrom(String fromAddress, String toAddress, BigInteger amount) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Transferring " + amount.toString() + " tokens from " + fromAddress + " to " + toAddress);
        return contract.transferFrom(fromAddress, toAddress, amount).sendAsync();
    }

    /**
     * Get the allowance that a spender has for an owner
     * @param ownerAddress the owner's address
     * @param spenderAddress the spender's address
     * @return the allowance amount
     */
    public CompletableFuture<BigInteger> allowance(String ownerAddress, String spenderAddress) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Getting allowance for spender " + spenderAddress + " from owner " + ownerAddress);
        return contract.allowance(ownerAddress, spenderAddress).sendAsync();
    }

    /**
     * Get token name
     * @return the token name
     */
    public CompletableFuture<String> name() {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Getting token name");
        return contract.name().sendAsync();
    }

    /**
     * Get token symbol
     * @return the token symbol
     */
    public CompletableFuture<String> symbol() {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Getting token symbol");
        return contract.symbol().sendAsync();
    }

    /**
     * Get token decimals
     * @return the token decimals
     */
    public CompletableFuture<BigInteger> decimals() {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Getting token decimals");
        return contract.decimals().sendAsync();
    }

    /**
     * Get total supply
     * @return the total supply
     */
    public CompletableFuture<BigInteger> totalSupply() {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Getting total supply");
        return contract.totalSupply().sendAsync();
    }

    /**
     * Burn tokens from the caller's account
     * @param amount the amount to burn
     * @return the transaction receipt
     */
    public CompletableFuture<TransactionReceipt> burn(BigInteger amount) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Burning " + amount.toString() + " tokens");
        return contract.burn(amount).sendAsync();
    }

    /**
     * Burn tokens from a specific address (requires allowance)
     * @param fromAddress the address to burn from
     * @param amount the amount to burn
     * @return the transaction receipt
     */
    public CompletableFuture<TransactionReceipt> burnFrom(String fromAddress, BigInteger amount) {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded");
        }
        log.info("Burning " + amount.toString() + " tokens from address: " + fromAddress);
        return contract.burnFrom(fromAddress, amount).sendAsync();
    }
}