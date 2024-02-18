/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import com.google.gson.annotations.JsonAdapter;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The server can signal these capabilities
 */
@SuppressWarnings("all")
public class ServerCapabilities {
  /**
   * The position encoding the server picked from the encodings offered
   * by the client via the client capability {@link GeneralClientCapabilities#positionEncodings}.
   * <p>
   * If the client didn't provide any position encodings the only valid
   * value that a server can return is {@link PositionEncodingKind#UTF16}.
   * <p>
   * If omitted it defaults to {@link PositionEncodingKind#UTF16}.
   * <p>
   * See {@link PositionEncodingKind} for some predefined position encoding kinds.
   * <p>
   * Since 3.17.0
   */
  private String positionEncoding;

  /**
   * Defines how text documents are synced. Is either a detailed structure defining each notification or
   * for backwards compatibility the TextDocumentSyncKind number. If omitted it defaults to
   * {@link TextDocumentSyncKind#None}.
   */
  private Either<TextDocumentSyncKind, TextDocumentSyncOptions> textDocumentSync;

  /**
   * Defines how notebook documents are synced.
   * <p>
   * Since 3.17.0
   */
  private NotebookDocumentSyncRegistrationOptions notebookDocumentSync;

  /**
   * The server provides hover support.
   */
  private Either<Boolean, HoverOptions> hoverProvider;

  /**
   * The server provides completion support.
   */
  private CompletionOptions completionProvider;

  /**
   * The server provides signature help support.
   */
  private SignatureHelpOptions signatureHelpProvider;

  /**
   * The server provides goto definition support.
   */
  private Either<Boolean, DefinitionOptions> definitionProvider;

  /**
   * The server provides Goto Type Definition support.
   * <p>
   * Since 3.6.0
   */
  private Either<Boolean, TypeDefinitionRegistrationOptions> typeDefinitionProvider;

  /**
   * The server provides Goto Implementation support.
   * <p>
   * Since 3.6.0
   */
  private Either<Boolean, ImplementationRegistrationOptions> implementationProvider;

  /**
   * The server provides find references support.
   */
  private Either<Boolean, ReferenceOptions> referencesProvider;

  /**
   * The server provides document highlight support.
   */
  private Either<Boolean, DocumentHighlightOptions> documentHighlightProvider;

  /**
   * The server provides document symbol support.
   */
  private Either<Boolean, DocumentSymbolOptions> documentSymbolProvider;

  /**
   * The server provides workspace symbol support.
   */
  private Either<Boolean, WorkspaceSymbolOptions> workspaceSymbolProvider;

  /**
   * The server provides code actions. The {@link CodeActionOptions} return type is only
   * valid if the client signals code action literal support via the property
   * {@link CodeActionCapabilities#codeActionLiteralSupport}.
   */
  private Either<Boolean, CodeActionOptions> codeActionProvider;

  /**
   * The server provides code lens.
   */
  private CodeLensOptions codeLensProvider;

  /**
   * The server provides document formatting.
   */
  private Either<Boolean, DocumentFormattingOptions> documentFormattingProvider;

  /**
   * The server provides document range formatting.
   */
  private Either<Boolean, DocumentRangeFormattingOptions> documentRangeFormattingProvider;

  /**
   * The server provides document formatting on typing.
   */
  private DocumentOnTypeFormattingOptions documentOnTypeFormattingProvider;

  /**
   * The server provides rename support.
   */
  private Either<Boolean, RenameOptions> renameProvider;

  /**
   * The server provides document link support.
   */
  private DocumentLinkOptions documentLinkProvider;

  /**
   * The server provides color provider support.
   * <p>
   * Since 3.6.0
   */
  private Either<Boolean, ColorProviderOptions> colorProvider;

  /**
   * The server provides folding provider support.
   * <p>
   * Since 3.10.0
   */
  private Either<Boolean, FoldingRangeProviderOptions> foldingRangeProvider;

  /**
   * The server provides go to declaration support.
   * <p>
   * Since 3.14.0
   */
  private Either<Boolean, DeclarationRegistrationOptions> declarationProvider;

  /**
   * The server provides execute command support.
   */
  private ExecuteCommandOptions executeCommandProvider;

  /**
   * Workspace specific server capabilities
   */
  private WorkspaceServerCapabilities workspace;

  /**
   * The server provides Type Hierarchy support.
   * <p>
   * Since 3.17.0
   */
  private Either<Boolean, TypeHierarchyRegistrationOptions> typeHierarchyProvider;

  /**
   * The server provides Call Hierarchy support.
   * <p>
   * Since 3.16.0
   */
  private Either<Boolean, CallHierarchyRegistrationOptions> callHierarchyProvider;

  /**
   * The server provides selection range support.
   * <p>
   * Since 3.15.0
   */
  private Either<Boolean, SelectionRangeRegistrationOptions> selectionRangeProvider;

  /**
   * The server provides linked editing range support.
   * <p>
   * Since 3.16.0
   */
  private Either<Boolean, LinkedEditingRangeRegistrationOptions> linkedEditingRangeProvider;

  /**
   * The server provides semantic tokens support.
   * <p>
   * Since 3.16.0
   */
  private SemanticTokensWithRegistrationOptions semanticTokensProvider;

  /**
   * Whether server provides moniker support.
   * <p>
   * Since 3.16.0
   */
  private Either<Boolean, MonikerRegistrationOptions> monikerProvider;

  /**
   * The server provides inlay hints.
   * <p>
   * Since 3.17.0
   */
  private Either<Boolean, InlayHintRegistrationOptions> inlayHintProvider;

  /**
   * The server provides inline values.
   * <p>
   * Since 3.17.0
   */
  private Either<Boolean, InlineValueRegistrationOptions> inlineValueProvider;

  /**
   * The server has support for pull model diagnostics.
   * <p>
   * Since 3.17.0
   */
  private DiagnosticRegistrationOptions diagnosticProvider;

  /**
   * Experimental server capabilities.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object experimental;

  /**
   * The position encoding the server picked from the encodings offered
   * by the client via the client capability {@link GeneralClientCapabilities#positionEncodings}.
   * <p>
   * If the client didn't provide any position encodings the only valid
   * value that a server can return is {@link PositionEncodingKind#UTF16}.
   * <p>
   * If omitted it defaults to {@link PositionEncodingKind#UTF16}.
   * <p>
   * See {@link PositionEncodingKind} for some predefined position encoding kinds.
   * <p>
   * Since 3.17.0
   */
  public String getPositionEncoding() {
    return this.positionEncoding;
  }

  /**
   * The position encoding the server picked from the encodings offered
   * by the client via the client capability {@link GeneralClientCapabilities#positionEncodings}.
   * <p>
   * If the client didn't provide any position encodings the only valid
   * value that a server can return is {@link PositionEncodingKind#UTF16}.
   * <p>
   * If omitted it defaults to {@link PositionEncodingKind#UTF16}.
   * <p>
   * See {@link PositionEncodingKind} for some predefined position encoding kinds.
   * <p>
   * Since 3.17.0
   */
  public void setPositionEncoding(final String positionEncoding) {
    this.positionEncoding = positionEncoding;
  }

  /**
   * Defines how text documents are synced. Is either a detailed structure defining each notification or
   * for backwards compatibility the TextDocumentSyncKind number. If omitted it defaults to
   * {@link TextDocumentSyncKind#None}.
   */
  public Either<TextDocumentSyncKind, TextDocumentSyncOptions> getTextDocumentSync() {
    return this.textDocumentSync;
  }

  /**
   * Defines how text documents are synced. Is either a detailed structure defining each notification or
   * for backwards compatibility the TextDocumentSyncKind number. If omitted it defaults to
   * {@link TextDocumentSyncKind#None}.
   */
  public void setTextDocumentSync(final Either<TextDocumentSyncKind, TextDocumentSyncOptions> textDocumentSync) {
    this.textDocumentSync = textDocumentSync;
  }

  public void setTextDocumentSync(final TextDocumentSyncKind textDocumentSync) {
    if (textDocumentSync == null) {
      this.textDocumentSync = null;
      return;
    }
    this.textDocumentSync = Either.forLeft(textDocumentSync);
  }

  public void setTextDocumentSync(final TextDocumentSyncOptions textDocumentSync) {
    if (textDocumentSync == null) {
      this.textDocumentSync = null;
      return;
    }
    this.textDocumentSync = Either.forRight(textDocumentSync);
  }

  /**
   * Defines how notebook documents are synced.
   * <p>
   * Since 3.17.0
   */
  public NotebookDocumentSyncRegistrationOptions getNotebookDocumentSync() {
    return this.notebookDocumentSync;
  }

  /**
   * Defines how notebook documents are synced.
   * <p>
   * Since 3.17.0
   */
  public void setNotebookDocumentSync(final NotebookDocumentSyncRegistrationOptions notebookDocumentSync) {
    this.notebookDocumentSync = notebookDocumentSync;
  }

  /**
   * The server provides hover support.
   */
  public Either<Boolean, HoverOptions> getHoverProvider() {
    return this.hoverProvider;
  }

  /**
   * The server provides hover support.
   */
  public void setHoverProvider(final Either<Boolean, HoverOptions> hoverProvider) {
    this.hoverProvider = hoverProvider;
  }

  public void setHoverProvider(final Boolean hoverProvider) {
    if (hoverProvider == null) {
      this.hoverProvider = null;
      return;
    }
    this.hoverProvider = Either.forLeft(hoverProvider);
  }

  public void setHoverProvider(final HoverOptions hoverProvider) {
    if (hoverProvider == null) {
      this.hoverProvider = null;
      return;
    }
    this.hoverProvider = Either.forRight(hoverProvider);
  }

  /**
   * The server provides completion support.
   */
  public CompletionOptions getCompletionProvider() {
    return this.completionProvider;
  }

  /**
   * The server provides completion support.
   */
  public void setCompletionProvider(final CompletionOptions completionProvider) {
    this.completionProvider = completionProvider;
  }

  /**
   * The server provides signature help support.
   */
  public SignatureHelpOptions getSignatureHelpProvider() {
    return this.signatureHelpProvider;
  }

  /**
   * The server provides signature help support.
   */
  public void setSignatureHelpProvider(final SignatureHelpOptions signatureHelpProvider) {
    this.signatureHelpProvider = signatureHelpProvider;
  }

  /**
   * The server provides goto definition support.
   */
  public Either<Boolean, DefinitionOptions> getDefinitionProvider() {
    return this.definitionProvider;
  }

  /**
   * The server provides goto definition support.
   */
  public void setDefinitionProvider(final Either<Boolean, DefinitionOptions> definitionProvider) {
    this.definitionProvider = definitionProvider;
  }

  public void setDefinitionProvider(final Boolean definitionProvider) {
    if (definitionProvider == null) {
      this.definitionProvider = null;
      return;
    }
    this.definitionProvider = Either.forLeft(definitionProvider);
  }

  public void setDefinitionProvider(final DefinitionOptions definitionProvider) {
    if (definitionProvider == null) {
      this.definitionProvider = null;
      return;
    }
    this.definitionProvider = Either.forRight(definitionProvider);
  }

  /**
   * The server provides Goto Type Definition support.
   * <p>
   * Since 3.6.0
   */
  public Either<Boolean, TypeDefinitionRegistrationOptions> getTypeDefinitionProvider() {
    return this.typeDefinitionProvider;
  }

  /**
   * The server provides Goto Type Definition support.
   * <p>
   * Since 3.6.0
   */
  public void setTypeDefinitionProvider(final Either<Boolean, TypeDefinitionRegistrationOptions> typeDefinitionProvider) {
    this.typeDefinitionProvider = typeDefinitionProvider;
  }

  public void setTypeDefinitionProvider(final Boolean typeDefinitionProvider) {
    if (typeDefinitionProvider == null) {
      this.typeDefinitionProvider = null;
      return;
    }
    this.typeDefinitionProvider = Either.forLeft(typeDefinitionProvider);
  }

  public void setTypeDefinitionProvider(final TypeDefinitionRegistrationOptions typeDefinitionProvider) {
    if (typeDefinitionProvider == null) {
      this.typeDefinitionProvider = null;
      return;
    }
    this.typeDefinitionProvider = Either.forRight(typeDefinitionProvider);
  }

  /**
   * The server provides Goto Implementation support.
   * <p>
   * Since 3.6.0
   */
  public Either<Boolean, ImplementationRegistrationOptions> getImplementationProvider() {
    return this.implementationProvider;
  }

  /**
   * The server provides Goto Implementation support.
   * <p>
   * Since 3.6.0
   */
  public void setImplementationProvider(final Either<Boolean, ImplementationRegistrationOptions> implementationProvider) {
    this.implementationProvider = implementationProvider;
  }

  public void setImplementationProvider(final Boolean implementationProvider) {
    if (implementationProvider == null) {
      this.implementationProvider = null;
      return;
    }
    this.implementationProvider = Either.forLeft(implementationProvider);
  }

  public void setImplementationProvider(final ImplementationRegistrationOptions implementationProvider) {
    if (implementationProvider == null) {
      this.implementationProvider = null;
      return;
    }
    this.implementationProvider = Either.forRight(implementationProvider);
  }

  /**
   * The server provides find references support.
   */
  public Either<Boolean, ReferenceOptions> getReferencesProvider() {
    return this.referencesProvider;
  }

  /**
   * The server provides find references support.
   */
  public void setReferencesProvider(final Either<Boolean, ReferenceOptions> referencesProvider) {
    this.referencesProvider = referencesProvider;
  }

  public void setReferencesProvider(final Boolean referencesProvider) {
    if (referencesProvider == null) {
      this.referencesProvider = null;
      return;
    }
    this.referencesProvider = Either.forLeft(referencesProvider);
  }

  public void setReferencesProvider(final ReferenceOptions referencesProvider) {
    if (referencesProvider == null) {
      this.referencesProvider = null;
      return;
    }
    this.referencesProvider = Either.forRight(referencesProvider);
  }

  /**
   * The server provides document highlight support.
   */
  public Either<Boolean, DocumentHighlightOptions> getDocumentHighlightProvider() {
    return this.documentHighlightProvider;
  }

  /**
   * The server provides document highlight support.
   */
  public void setDocumentHighlightProvider(final Either<Boolean, DocumentHighlightOptions> documentHighlightProvider) {
    this.documentHighlightProvider = documentHighlightProvider;
  }

  public void setDocumentHighlightProvider(final Boolean documentHighlightProvider) {
    if (documentHighlightProvider == null) {
      this.documentHighlightProvider = null;
      return;
    }
    this.documentHighlightProvider = Either.forLeft(documentHighlightProvider);
  }

  public void setDocumentHighlightProvider(final DocumentHighlightOptions documentHighlightProvider) {
    if (documentHighlightProvider == null) {
      this.documentHighlightProvider = null;
      return;
    }
    this.documentHighlightProvider = Either.forRight(documentHighlightProvider);
  }

  /**
   * The server provides document symbol support.
   */
  public Either<Boolean, DocumentSymbolOptions> getDocumentSymbolProvider() {
    return this.documentSymbolProvider;
  }

  /**
   * The server provides document symbol support.
   */
  public void setDocumentSymbolProvider(final Either<Boolean, DocumentSymbolOptions> documentSymbolProvider) {
    this.documentSymbolProvider = documentSymbolProvider;
  }

  public void setDocumentSymbolProvider(final Boolean documentSymbolProvider) {
    if (documentSymbolProvider == null) {
      this.documentSymbolProvider = null;
      return;
    }
    this.documentSymbolProvider = Either.forLeft(documentSymbolProvider);
  }

  public void setDocumentSymbolProvider(final DocumentSymbolOptions documentSymbolProvider) {
    if (documentSymbolProvider == null) {
      this.documentSymbolProvider = null;
      return;
    }
    this.documentSymbolProvider = Either.forRight(documentSymbolProvider);
  }

  /**
   * The server provides workspace symbol support.
   */
  public Either<Boolean, WorkspaceSymbolOptions> getWorkspaceSymbolProvider() {
    return this.workspaceSymbolProvider;
  }

  /**
   * The server provides workspace symbol support.
   */
  public void setWorkspaceSymbolProvider(final Either<Boolean, WorkspaceSymbolOptions> workspaceSymbolProvider) {
    this.workspaceSymbolProvider = workspaceSymbolProvider;
  }

  public void setWorkspaceSymbolProvider(final Boolean workspaceSymbolProvider) {
    if (workspaceSymbolProvider == null) {
      this.workspaceSymbolProvider = null;
      return;
    }
    this.workspaceSymbolProvider = Either.forLeft(workspaceSymbolProvider);
  }

  public void setWorkspaceSymbolProvider(final WorkspaceSymbolOptions workspaceSymbolProvider) {
    if (workspaceSymbolProvider == null) {
      this.workspaceSymbolProvider = null;
      return;
    }
    this.workspaceSymbolProvider = Either.forRight(workspaceSymbolProvider);
  }

  /**
   * The server provides code actions. The {@link CodeActionOptions} return type is only
   * valid if the client signals code action literal support via the property
   * {@link CodeActionCapabilities#codeActionLiteralSupport}.
   */
  public Either<Boolean, CodeActionOptions> getCodeActionProvider() {
    return this.codeActionProvider;
  }

  /**
   * The server provides code actions. The {@link CodeActionOptions} return type is only
   * valid if the client signals code action literal support via the property
   * {@link CodeActionCapabilities#codeActionLiteralSupport}.
   */
  public void setCodeActionProvider(final Either<Boolean, CodeActionOptions> codeActionProvider) {
    this.codeActionProvider = codeActionProvider;
  }

  public void setCodeActionProvider(final Boolean codeActionProvider) {
    if (codeActionProvider == null) {
      this.codeActionProvider = null;
      return;
    }
    this.codeActionProvider = Either.forLeft(codeActionProvider);
  }

  public void setCodeActionProvider(final CodeActionOptions codeActionProvider) {
    if (codeActionProvider == null) {
      this.codeActionProvider = null;
      return;
    }
    this.codeActionProvider = Either.forRight(codeActionProvider);
  }

  /**
   * The server provides code lens.
   */
  public CodeLensOptions getCodeLensProvider() {
    return this.codeLensProvider;
  }

  /**
   * The server provides code lens.
   */
  public void setCodeLensProvider(final CodeLensOptions codeLensProvider) {
    this.codeLensProvider = codeLensProvider;
  }

  /**
   * The server provides document formatting.
   */
  public Either<Boolean, DocumentFormattingOptions> getDocumentFormattingProvider() {
    return this.documentFormattingProvider;
  }

  /**
   * The server provides document formatting.
   */
  public void setDocumentFormattingProvider(final Either<Boolean, DocumentFormattingOptions> documentFormattingProvider) {
    this.documentFormattingProvider = documentFormattingProvider;
  }

  public void setDocumentFormattingProvider(final Boolean documentFormattingProvider) {
    if (documentFormattingProvider == null) {
      this.documentFormattingProvider = null;
      return;
    }
    this.documentFormattingProvider = Either.forLeft(documentFormattingProvider);
  }

  public void setDocumentFormattingProvider(final DocumentFormattingOptions documentFormattingProvider) {
    if (documentFormattingProvider == null) {
      this.documentFormattingProvider = null;
      return;
    }
    this.documentFormattingProvider = Either.forRight(documentFormattingProvider);
  }

  /**
   * The server provides document range formatting.
   */
  public Either<Boolean, DocumentRangeFormattingOptions> getDocumentRangeFormattingProvider() {
    return this.documentRangeFormattingProvider;
  }

  /**
   * The server provides document range formatting.
   */
  public void setDocumentRangeFormattingProvider(final Either<Boolean, DocumentRangeFormattingOptions> documentRangeFormattingProvider) {
    this.documentRangeFormattingProvider = documentRangeFormattingProvider;
  }

  public void setDocumentRangeFormattingProvider(final Boolean documentRangeFormattingProvider) {
    if (documentRangeFormattingProvider == null) {
      this.documentRangeFormattingProvider = null;
      return;
    }
    this.documentRangeFormattingProvider = Either.forLeft(documentRangeFormattingProvider);
  }

  public void setDocumentRangeFormattingProvider(final DocumentRangeFormattingOptions documentRangeFormattingProvider) {
    if (documentRangeFormattingProvider == null) {
      this.documentRangeFormattingProvider = null;
      return;
    }
    this.documentRangeFormattingProvider = Either.forRight(documentRangeFormattingProvider);
  }

  /**
   * The server provides document formatting on typing.
   */
  public DocumentOnTypeFormattingOptions getDocumentOnTypeFormattingProvider() {
    return this.documentOnTypeFormattingProvider;
  }

  /**
   * The server provides document formatting on typing.
   */
  public void setDocumentOnTypeFormattingProvider(final DocumentOnTypeFormattingOptions documentOnTypeFormattingProvider) {
    this.documentOnTypeFormattingProvider = documentOnTypeFormattingProvider;
  }

  /**
   * The server provides rename support.
   */
  public Either<Boolean, RenameOptions> getRenameProvider() {
    return this.renameProvider;
  }

  /**
   * The server provides rename support.
   */
  public void setRenameProvider(final Either<Boolean, RenameOptions> renameProvider) {
    this.renameProvider = renameProvider;
  }

  public void setRenameProvider(final Boolean renameProvider) {
    if (renameProvider == null) {
      this.renameProvider = null;
      return;
    }
    this.renameProvider = Either.forLeft(renameProvider);
  }

  public void setRenameProvider(final RenameOptions renameProvider) {
    if (renameProvider == null) {
      this.renameProvider = null;
      return;
    }
    this.renameProvider = Either.forRight(renameProvider);
  }

  /**
   * The server provides document link support.
   */
  public DocumentLinkOptions getDocumentLinkProvider() {
    return this.documentLinkProvider;
  }

  /**
   * The server provides document link support.
   */
  public void setDocumentLinkProvider(final DocumentLinkOptions documentLinkProvider) {
    this.documentLinkProvider = documentLinkProvider;
  }

  /**
   * The server provides color provider support.
   * <p>
   * Since 3.6.0
   */
  public Either<Boolean, ColorProviderOptions> getColorProvider() {
    return this.colorProvider;
  }

  /**
   * The server provides color provider support.
   * <p>
   * Since 3.6.0
   */
  public void setColorProvider(final Either<Boolean, ColorProviderOptions> colorProvider) {
    this.colorProvider = colorProvider;
  }

  public void setColorProvider(final Boolean colorProvider) {
    if (colorProvider == null) {
      this.colorProvider = null;
      return;
    }
    this.colorProvider = Either.forLeft(colorProvider);
  }

  public void setColorProvider(final ColorProviderOptions colorProvider) {
    if (colorProvider == null) {
      this.colorProvider = null;
      return;
    }
    this.colorProvider = Either.forRight(colorProvider);
  }

  /**
   * The server provides folding provider support.
   * <p>
   * Since 3.10.0
   */
  public Either<Boolean, FoldingRangeProviderOptions> getFoldingRangeProvider() {
    return this.foldingRangeProvider;
  }

  /**
   * The server provides folding provider support.
   * <p>
   * Since 3.10.0
   */
  public void setFoldingRangeProvider(final Either<Boolean, FoldingRangeProviderOptions> foldingRangeProvider) {
    this.foldingRangeProvider = foldingRangeProvider;
  }

  public void setFoldingRangeProvider(final Boolean foldingRangeProvider) {
    if (foldingRangeProvider == null) {
      this.foldingRangeProvider = null;
      return;
    }
    this.foldingRangeProvider = Either.forLeft(foldingRangeProvider);
  }

  public void setFoldingRangeProvider(final FoldingRangeProviderOptions foldingRangeProvider) {
    if (foldingRangeProvider == null) {
      this.foldingRangeProvider = null;
      return;
    }
    this.foldingRangeProvider = Either.forRight(foldingRangeProvider);
  }

  /**
   * The server provides go to declaration support.
   * <p>
   * Since 3.14.0
   */
  public Either<Boolean, DeclarationRegistrationOptions> getDeclarationProvider() {
    return this.declarationProvider;
  }

  /**
   * The server provides go to declaration support.
   * <p>
   * Since 3.14.0
   */
  public void setDeclarationProvider(final Either<Boolean, DeclarationRegistrationOptions> declarationProvider) {
    this.declarationProvider = declarationProvider;
  }

  public void setDeclarationProvider(final Boolean declarationProvider) {
    if (declarationProvider == null) {
      this.declarationProvider = null;
      return;
    }
    this.declarationProvider = Either.forLeft(declarationProvider);
  }

  public void setDeclarationProvider(final DeclarationRegistrationOptions declarationProvider) {
    if (declarationProvider == null) {
      this.declarationProvider = null;
      return;
    }
    this.declarationProvider = Either.forRight(declarationProvider);
  }

  /**
   * The server provides execute command support.
   */
  public ExecuteCommandOptions getExecuteCommandProvider() {
    return this.executeCommandProvider;
  }

  /**
   * The server provides execute command support.
   */
  public void setExecuteCommandProvider(final ExecuteCommandOptions executeCommandProvider) {
    this.executeCommandProvider = executeCommandProvider;
  }

  /**
   * Workspace specific server capabilities
   */
  public WorkspaceServerCapabilities getWorkspace() {
    return this.workspace;
  }

  /**
   * Workspace specific server capabilities
   */
  public void setWorkspace(final WorkspaceServerCapabilities workspace) {
    this.workspace = workspace;
  }

  /**
   * The server provides Type Hierarchy support.
   * <p>
   * Since 3.17.0
   */
  public Either<Boolean, TypeHierarchyRegistrationOptions> getTypeHierarchyProvider() {
    return this.typeHierarchyProvider;
  }

  /**
   * The server provides Type Hierarchy support.
   * <p>
   * Since 3.17.0
   */
  public void setTypeHierarchyProvider(final Either<Boolean, TypeHierarchyRegistrationOptions> typeHierarchyProvider) {
    this.typeHierarchyProvider = typeHierarchyProvider;
  }

  public void setTypeHierarchyProvider(final Boolean typeHierarchyProvider) {
    if (typeHierarchyProvider == null) {
      this.typeHierarchyProvider = null;
      return;
    }
    this.typeHierarchyProvider = Either.forLeft(typeHierarchyProvider);
  }

  public void setTypeHierarchyProvider(final TypeHierarchyRegistrationOptions typeHierarchyProvider) {
    if (typeHierarchyProvider == null) {
      this.typeHierarchyProvider = null;
      return;
    }
    this.typeHierarchyProvider = Either.forRight(typeHierarchyProvider);
  }

  /**
   * The server provides Call Hierarchy support.
   * <p>
   * Since 3.16.0
   */
  public Either<Boolean, CallHierarchyRegistrationOptions> getCallHierarchyProvider() {
    return this.callHierarchyProvider;
  }

  /**
   * The server provides Call Hierarchy support.
   * <p>
   * Since 3.16.0
   */
  public void setCallHierarchyProvider(final Either<Boolean, CallHierarchyRegistrationOptions> callHierarchyProvider) {
    this.callHierarchyProvider = callHierarchyProvider;
  }

  public void setCallHierarchyProvider(final Boolean callHierarchyProvider) {
    if (callHierarchyProvider == null) {
      this.callHierarchyProvider = null;
      return;
    }
    this.callHierarchyProvider = Either.forLeft(callHierarchyProvider);
  }

  public void setCallHierarchyProvider(final CallHierarchyRegistrationOptions callHierarchyProvider) {
    if (callHierarchyProvider == null) {
      this.callHierarchyProvider = null;
      return;
    }
    this.callHierarchyProvider = Either.forRight(callHierarchyProvider);
  }

  /**
   * The server provides selection range support.
   * <p>
   * Since 3.15.0
   */
  public Either<Boolean, SelectionRangeRegistrationOptions> getSelectionRangeProvider() {
    return this.selectionRangeProvider;
  }

  /**
   * The server provides selection range support.
   * <p>
   * Since 3.15.0
   */
  public void setSelectionRangeProvider(final Either<Boolean, SelectionRangeRegistrationOptions> selectionRangeProvider) {
    this.selectionRangeProvider = selectionRangeProvider;
  }

  public void setSelectionRangeProvider(final Boolean selectionRangeProvider) {
    if (selectionRangeProvider == null) {
      this.selectionRangeProvider = null;
      return;
    }
    this.selectionRangeProvider = Either.forLeft(selectionRangeProvider);
  }

  public void setSelectionRangeProvider(final SelectionRangeRegistrationOptions selectionRangeProvider) {
    if (selectionRangeProvider == null) {
      this.selectionRangeProvider = null;
      return;
    }
    this.selectionRangeProvider = Either.forRight(selectionRangeProvider);
  }

  /**
   * The server provides linked editing range support.
   * <p>
   * Since 3.16.0
   */
  public Either<Boolean, LinkedEditingRangeRegistrationOptions> getLinkedEditingRangeProvider() {
    return this.linkedEditingRangeProvider;
  }

  /**
   * The server provides linked editing range support.
   * <p>
   * Since 3.16.0
   */
  public void setLinkedEditingRangeProvider(final Either<Boolean, LinkedEditingRangeRegistrationOptions> linkedEditingRangeProvider) {
    this.linkedEditingRangeProvider = linkedEditingRangeProvider;
  }

  public void setLinkedEditingRangeProvider(final Boolean linkedEditingRangeProvider) {
    if (linkedEditingRangeProvider == null) {
      this.linkedEditingRangeProvider = null;
      return;
    }
    this.linkedEditingRangeProvider = Either.forLeft(linkedEditingRangeProvider);
  }

  public void setLinkedEditingRangeProvider(final LinkedEditingRangeRegistrationOptions linkedEditingRangeProvider) {
    if (linkedEditingRangeProvider == null) {
      this.linkedEditingRangeProvider = null;
      return;
    }
    this.linkedEditingRangeProvider = Either.forRight(linkedEditingRangeProvider);
  }

  /**
   * The server provides semantic tokens support.
   * <p>
   * Since 3.16.0
   */
  public SemanticTokensWithRegistrationOptions getSemanticTokensProvider() {
    return this.semanticTokensProvider;
  }

  /**
   * The server provides semantic tokens support.
   * <p>
   * Since 3.16.0
   */
  public void setSemanticTokensProvider(final SemanticTokensWithRegistrationOptions semanticTokensProvider) {
    this.semanticTokensProvider = semanticTokensProvider;
  }

  /**
   * Whether server provides moniker support.
   * <p>
   * Since 3.16.0
   */
  public Either<Boolean, MonikerRegistrationOptions> getMonikerProvider() {
    return this.monikerProvider;
  }

  /**
   * Whether server provides moniker support.
   * <p>
   * Since 3.16.0
   */
  public void setMonikerProvider(final Either<Boolean, MonikerRegistrationOptions> monikerProvider) {
    this.monikerProvider = monikerProvider;
  }

  public void setMonikerProvider(final Boolean monikerProvider) {
    if (monikerProvider == null) {
      this.monikerProvider = null;
      return;
    }
    this.monikerProvider = Either.forLeft(monikerProvider);
  }

  public void setMonikerProvider(final MonikerRegistrationOptions monikerProvider) {
    if (monikerProvider == null) {
      this.monikerProvider = null;
      return;
    }
    this.monikerProvider = Either.forRight(monikerProvider);
  }

  /**
   * The server provides inlay hints.
   * <p>
   * Since 3.17.0
   */
  public Either<Boolean, InlayHintRegistrationOptions> getInlayHintProvider() {
    return this.inlayHintProvider;
  }

  /**
   * The server provides inlay hints.
   * <p>
   * Since 3.17.0
   */
  public void setInlayHintProvider(final Either<Boolean, InlayHintRegistrationOptions> inlayHintProvider) {
    this.inlayHintProvider = inlayHintProvider;
  }

  public void setInlayHintProvider(final Boolean inlayHintProvider) {
    if (inlayHintProvider == null) {
      this.inlayHintProvider = null;
      return;
    }
    this.inlayHintProvider = Either.forLeft(inlayHintProvider);
  }

  public void setInlayHintProvider(final InlayHintRegistrationOptions inlayHintProvider) {
    if (inlayHintProvider == null) {
      this.inlayHintProvider = null;
      return;
    }
    this.inlayHintProvider = Either.forRight(inlayHintProvider);
  }

  /**
   * The server provides inline values.
   * <p>
   * Since 3.17.0
   */
  public Either<Boolean, InlineValueRegistrationOptions> getInlineValueProvider() {
    return this.inlineValueProvider;
  }

  /**
   * The server provides inline values.
   * <p>
   * Since 3.17.0
   */
  public void setInlineValueProvider(final Either<Boolean, InlineValueRegistrationOptions> inlineValueProvider) {
    this.inlineValueProvider = inlineValueProvider;
  }

  public void setInlineValueProvider(final Boolean inlineValueProvider) {
    if (inlineValueProvider == null) {
      this.inlineValueProvider = null;
      return;
    }
    this.inlineValueProvider = Either.forLeft(inlineValueProvider);
  }

  public void setInlineValueProvider(final InlineValueRegistrationOptions inlineValueProvider) {
    if (inlineValueProvider == null) {
      this.inlineValueProvider = null;
      return;
    }
    this.inlineValueProvider = Either.forRight(inlineValueProvider);
  }

  /**
   * The server has support for pull model diagnostics.
   * <p>
   * Since 3.17.0
   */
  public DiagnosticRegistrationOptions getDiagnosticProvider() {
    return this.diagnosticProvider;
  }

  /**
   * The server has support for pull model diagnostics.
   * <p>
   * Since 3.17.0
   */
  public void setDiagnosticProvider(final DiagnosticRegistrationOptions diagnosticProvider) {
    this.diagnosticProvider = diagnosticProvider;
  }

  /**
   * Experimental server capabilities.
   */
  public Object getExperimental() {
    return this.experimental;
  }

  /**
   * Experimental server capabilities.
   */
  public void setExperimental(final Object experimental) {
    this.experimental = experimental;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("positionEncoding", this.positionEncoding);
    b.add("textDocumentSync", this.textDocumentSync);
    b.add("notebookDocumentSync", this.notebookDocumentSync);
    b.add("hoverProvider", this.hoverProvider);
    b.add("completionProvider", this.completionProvider);
    b.add("signatureHelpProvider", this.signatureHelpProvider);
    b.add("definitionProvider", this.definitionProvider);
    b.add("typeDefinitionProvider", this.typeDefinitionProvider);
    b.add("implementationProvider", this.implementationProvider);
    b.add("referencesProvider", this.referencesProvider);
    b.add("documentHighlightProvider", this.documentHighlightProvider);
    b.add("documentSymbolProvider", this.documentSymbolProvider);
    b.add("workspaceSymbolProvider", this.workspaceSymbolProvider);
    b.add("codeActionProvider", this.codeActionProvider);
    b.add("codeLensProvider", this.codeLensProvider);
    b.add("documentFormattingProvider", this.documentFormattingProvider);
    b.add("documentRangeFormattingProvider", this.documentRangeFormattingProvider);
    b.add("documentOnTypeFormattingProvider", this.documentOnTypeFormattingProvider);
    b.add("renameProvider", this.renameProvider);
    b.add("documentLinkProvider", this.documentLinkProvider);
    b.add("colorProvider", this.colorProvider);
    b.add("foldingRangeProvider", this.foldingRangeProvider);
    b.add("declarationProvider", this.declarationProvider);
    b.add("executeCommandProvider", this.executeCommandProvider);
    b.add("workspace", this.workspace);
    b.add("typeHierarchyProvider", this.typeHierarchyProvider);
    b.add("callHierarchyProvider", this.callHierarchyProvider);
    b.add("selectionRangeProvider", this.selectionRangeProvider);
    b.add("linkedEditingRangeProvider", this.linkedEditingRangeProvider);
    b.add("semanticTokensProvider", this.semanticTokensProvider);
    b.add("monikerProvider", this.monikerProvider);
    b.add("inlayHintProvider", this.inlayHintProvider);
    b.add("inlineValueProvider", this.inlineValueProvider);
    b.add("diagnosticProvider", this.diagnosticProvider);
    b.add("experimental", this.experimental);
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ServerCapabilities other = (ServerCapabilities) obj;
    if (this.positionEncoding == null) {
      if (other.positionEncoding != null)
        return false;
    } else if (!this.positionEncoding.equals(other.positionEncoding))
      return false;
    if (this.textDocumentSync == null) {
      if (other.textDocumentSync != null)
        return false;
    } else if (!this.textDocumentSync.equals(other.textDocumentSync))
      return false;
    if (this.notebookDocumentSync == null) {
      if (other.notebookDocumentSync != null)
        return false;
    } else if (!this.notebookDocumentSync.equals(other.notebookDocumentSync))
      return false;
    if (this.hoverProvider == null) {
      if (other.hoverProvider != null)
        return false;
    } else if (!this.hoverProvider.equals(other.hoverProvider))
      return false;
    if (this.completionProvider == null) {
      if (other.completionProvider != null)
        return false;
    } else if (!this.completionProvider.equals(other.completionProvider))
      return false;
    if (this.signatureHelpProvider == null) {
      if (other.signatureHelpProvider != null)
        return false;
    } else if (!this.signatureHelpProvider.equals(other.signatureHelpProvider))
      return false;
    if (this.definitionProvider == null) {
      if (other.definitionProvider != null)
        return false;
    } else if (!this.definitionProvider.equals(other.definitionProvider))
      return false;
    if (this.typeDefinitionProvider == null) {
      if (other.typeDefinitionProvider != null)
        return false;
    } else if (!this.typeDefinitionProvider.equals(other.typeDefinitionProvider))
      return false;
    if (this.implementationProvider == null) {
      if (other.implementationProvider != null)
        return false;
    } else if (!this.implementationProvider.equals(other.implementationProvider))
      return false;
    if (this.referencesProvider == null) {
      if (other.referencesProvider != null)
        return false;
    } else if (!this.referencesProvider.equals(other.referencesProvider))
      return false;
    if (this.documentHighlightProvider == null) {
      if (other.documentHighlightProvider != null)
        return false;
    } else if (!this.documentHighlightProvider.equals(other.documentHighlightProvider))
      return false;
    if (this.documentSymbolProvider == null) {
      if (other.documentSymbolProvider != null)
        return false;
    } else if (!this.documentSymbolProvider.equals(other.documentSymbolProvider))
      return false;
    if (this.workspaceSymbolProvider == null) {
      if (other.workspaceSymbolProvider != null)
        return false;
    } else if (!this.workspaceSymbolProvider.equals(other.workspaceSymbolProvider))
      return false;
    if (this.codeActionProvider == null) {
      if (other.codeActionProvider != null)
        return false;
    } else if (!this.codeActionProvider.equals(other.codeActionProvider))
      return false;
    if (this.codeLensProvider == null) {
      if (other.codeLensProvider != null)
        return false;
    } else if (!this.codeLensProvider.equals(other.codeLensProvider))
      return false;
    if (this.documentFormattingProvider == null) {
      if (other.documentFormattingProvider != null)
        return false;
    } else if (!this.documentFormattingProvider.equals(other.documentFormattingProvider))
      return false;
    if (this.documentRangeFormattingProvider == null) {
      if (other.documentRangeFormattingProvider != null)
        return false;
    } else if (!this.documentRangeFormattingProvider.equals(other.documentRangeFormattingProvider))
      return false;
    if (this.documentOnTypeFormattingProvider == null) {
      if (other.documentOnTypeFormattingProvider != null)
        return false;
    } else if (!this.documentOnTypeFormattingProvider.equals(other.documentOnTypeFormattingProvider))
      return false;
    if (this.renameProvider == null) {
      if (other.renameProvider != null)
        return false;
    } else if (!this.renameProvider.equals(other.renameProvider))
      return false;
    if (this.documentLinkProvider == null) {
      if (other.documentLinkProvider != null)
        return false;
    } else if (!this.documentLinkProvider.equals(other.documentLinkProvider))
      return false;
    if (this.colorProvider == null) {
      if (other.colorProvider != null)
        return false;
    } else if (!this.colorProvider.equals(other.colorProvider))
      return false;
    if (this.foldingRangeProvider == null) {
      if (other.foldingRangeProvider != null)
        return false;
    } else if (!this.foldingRangeProvider.equals(other.foldingRangeProvider))
      return false;
    if (this.declarationProvider == null) {
      if (other.declarationProvider != null)
        return false;
    } else if (!this.declarationProvider.equals(other.declarationProvider))
      return false;
    if (this.executeCommandProvider == null) {
      if (other.executeCommandProvider != null)
        return false;
    } else if (!this.executeCommandProvider.equals(other.executeCommandProvider))
      return false;
    if (this.workspace == null) {
      if (other.workspace != null)
        return false;
    } else if (!this.workspace.equals(other.workspace))
      return false;
    if (this.typeHierarchyProvider == null) {
      if (other.typeHierarchyProvider != null)
        return false;
    } else if (!this.typeHierarchyProvider.equals(other.typeHierarchyProvider))
      return false;
    if (this.callHierarchyProvider == null) {
      if (other.callHierarchyProvider != null)
        return false;
    } else if (!this.callHierarchyProvider.equals(other.callHierarchyProvider))
      return false;
    if (this.selectionRangeProvider == null) {
      if (other.selectionRangeProvider != null)
        return false;
    } else if (!this.selectionRangeProvider.equals(other.selectionRangeProvider))
      return false;
    if (this.linkedEditingRangeProvider == null) {
      if (other.linkedEditingRangeProvider != null)
        return false;
    } else if (!this.linkedEditingRangeProvider.equals(other.linkedEditingRangeProvider))
      return false;
    if (this.semanticTokensProvider == null) {
      if (other.semanticTokensProvider != null)
        return false;
    } else if (!this.semanticTokensProvider.equals(other.semanticTokensProvider))
      return false;
    if (this.monikerProvider == null) {
      if (other.monikerProvider != null)
        return false;
    } else if (!this.monikerProvider.equals(other.monikerProvider))
      return false;
    if (this.inlayHintProvider == null) {
      if (other.inlayHintProvider != null)
        return false;
    } else if (!this.inlayHintProvider.equals(other.inlayHintProvider))
      return false;
    if (this.inlineValueProvider == null) {
      if (other.inlineValueProvider != null)
        return false;
    } else if (!this.inlineValueProvider.equals(other.inlineValueProvider))
      return false;
    if (this.diagnosticProvider == null) {
      if (other.diagnosticProvider != null)
        return false;
    } else if (!this.diagnosticProvider.equals(other.diagnosticProvider))
      return false;
    if (this.experimental == null) {
      if (other.experimental != null)
        return false;
    } else if (!this.experimental.equals(other.experimental))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.positionEncoding== null) ? 0 : this.positionEncoding.hashCode());
    result = prime * result + ((this.textDocumentSync== null) ? 0 : this.textDocumentSync.hashCode());
    result = prime * result + ((this.notebookDocumentSync== null) ? 0 : this.notebookDocumentSync.hashCode());
    result = prime * result + ((this.hoverProvider== null) ? 0 : this.hoverProvider.hashCode());
    result = prime * result + ((this.completionProvider== null) ? 0 : this.completionProvider.hashCode());
    result = prime * result + ((this.signatureHelpProvider== null) ? 0 : this.signatureHelpProvider.hashCode());
    result = prime * result + ((this.definitionProvider== null) ? 0 : this.definitionProvider.hashCode());
    result = prime * result + ((this.typeDefinitionProvider== null) ? 0 : this.typeDefinitionProvider.hashCode());
    result = prime * result + ((this.implementationProvider== null) ? 0 : this.implementationProvider.hashCode());
    result = prime * result + ((this.referencesProvider== null) ? 0 : this.referencesProvider.hashCode());
    result = prime * result + ((this.documentHighlightProvider== null) ? 0 : this.documentHighlightProvider.hashCode());
    result = prime * result + ((this.documentSymbolProvider== null) ? 0 : this.documentSymbolProvider.hashCode());
    result = prime * result + ((this.workspaceSymbolProvider== null) ? 0 : this.workspaceSymbolProvider.hashCode());
    result = prime * result + ((this.codeActionProvider== null) ? 0 : this.codeActionProvider.hashCode());
    result = prime * result + ((this.codeLensProvider== null) ? 0 : this.codeLensProvider.hashCode());
    result = prime * result + ((this.documentFormattingProvider== null) ? 0 : this.documentFormattingProvider.hashCode());
    result = prime * result + ((this.documentRangeFormattingProvider== null) ? 0 : this.documentRangeFormattingProvider.hashCode());
    result = prime * result + ((this.documentOnTypeFormattingProvider== null) ? 0 : this.documentOnTypeFormattingProvider.hashCode());
    result = prime * result + ((this.renameProvider== null) ? 0 : this.renameProvider.hashCode());
    result = prime * result + ((this.documentLinkProvider== null) ? 0 : this.documentLinkProvider.hashCode());
    result = prime * result + ((this.colorProvider== null) ? 0 : this.colorProvider.hashCode());
    result = prime * result + ((this.foldingRangeProvider== null) ? 0 : this.foldingRangeProvider.hashCode());
    result = prime * result + ((this.declarationProvider== null) ? 0 : this.declarationProvider.hashCode());
    result = prime * result + ((this.executeCommandProvider== null) ? 0 : this.executeCommandProvider.hashCode());
    result = prime * result + ((this.workspace== null) ? 0 : this.workspace.hashCode());
    result = prime * result + ((this.typeHierarchyProvider== null) ? 0 : this.typeHierarchyProvider.hashCode());
    result = prime * result + ((this.callHierarchyProvider== null) ? 0 : this.callHierarchyProvider.hashCode());
    result = prime * result + ((this.selectionRangeProvider== null) ? 0 : this.selectionRangeProvider.hashCode());
    result = prime * result + ((this.linkedEditingRangeProvider== null) ? 0 : this.linkedEditingRangeProvider.hashCode());
    result = prime * result + ((this.semanticTokensProvider== null) ? 0 : this.semanticTokensProvider.hashCode());
    result = prime * result + ((this.monikerProvider== null) ? 0 : this.monikerProvider.hashCode());
    result = prime * result + ((this.inlayHintProvider== null) ? 0 : this.inlayHintProvider.hashCode());
    result = prime * result + ((this.inlineValueProvider== null) ? 0 : this.inlineValueProvider.hashCode());
    result = prime * result + ((this.diagnosticProvider== null) ? 0 : this.diagnosticProvider.hashCode());
    return prime * result + ((this.experimental== null) ? 0 : this.experimental.hashCode());
  }
}
