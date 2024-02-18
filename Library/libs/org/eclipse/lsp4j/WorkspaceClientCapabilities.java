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

import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Workspace specific client capabilities.
 */
@SuppressWarnings("all")
public class WorkspaceClientCapabilities {
  /**
   * The client supports applying batch edits to the workspace by supporting
   * the request 'workspace/applyEdit'.
   */
  private Boolean applyEdit;

  /**
   * Capabilities specific to {@link WorkspaceEdit}s
   */
  private WorkspaceEditCapabilities workspaceEdit;

  /**
   * Capabilities specific to the `workspace/didChangeConfiguration` notification.
   */
  private DidChangeConfigurationCapabilities didChangeConfiguration;

  /**
   * Capabilities specific to the `workspace/didChangeWatchedFiles` notification.
   */
  private DidChangeWatchedFilesCapabilities didChangeWatchedFiles;

  /**
   * Capabilities specific to the `workspace/symbol` request.
   */
  private SymbolCapabilities symbol;

  /**
   * Capabilities specific to the `workspace/executeCommand` request.
   */
  private ExecuteCommandCapabilities executeCommand;

  /**
   * The client has support for workspace folders.
   * <p>
   * Since 3.6.0
   */
  private Boolean workspaceFolders;

  /**
   * The client supports `workspace/configuration` requests.
   * <p>
   * Since 3.6.0
   */
  private Boolean configuration;

  /**
   * Capabilities specific to the semantic token requests scoped to the
   * workspace.
   * <p>
   * Since 3.16.0
   */
  private SemanticTokensWorkspaceCapabilities semanticTokens;

  /**
   * Capabilities specific to the code lens requests scoped to the
   * workspace.
   * <p>
   * Since 3.16.0
   */
  private CodeLensWorkspaceCapabilities codeLens;

  /**
   * The client has support for file requests/notifications.
   * <p>
   * Since 3.16.0
   */
  private FileOperationsWorkspaceCapabilities fileOperations;

  /**
   * Client workspace capabilities specific to inlay hints.
   * <p>
   * Since 3.17.0
   */
  private InlayHintWorkspaceCapabilities inlayHint;

  /**
   * Client workspace capabilities specific to inline values.
   * <p>
   * Since 3.17.0
   */
  private InlineValueWorkspaceCapabilities inlineValue;

  /**
   * Client workspace capabilities specific to diagnostics.
   * <p>
   * Since 3.17.0
   */
  private DiagnosticWorkspaceCapabilities diagnostics;

  public WorkspaceClientCapabilities() {
  }

  /**
   * The client supports applying batch edits to the workspace by supporting
   * the request 'workspace/applyEdit'.
   */
  public Boolean getApplyEdit() {
    return this.applyEdit;
  }

  /**
   * The client supports applying batch edits to the workspace by supporting
   * the request 'workspace/applyEdit'.
   */
  public void setApplyEdit(final Boolean applyEdit) {
    this.applyEdit = applyEdit;
  }

  /**
   * Capabilities specific to {@link WorkspaceEdit}s
   */
  public WorkspaceEditCapabilities getWorkspaceEdit() {
    return this.workspaceEdit;
  }

  /**
   * Capabilities specific to {@link WorkspaceEdit}s
   */
  public void setWorkspaceEdit(final WorkspaceEditCapabilities workspaceEdit) {
    this.workspaceEdit = workspaceEdit;
  }

  /**
   * Capabilities specific to the `workspace/didChangeConfiguration` notification.
   */
  public DidChangeConfigurationCapabilities getDidChangeConfiguration() {
    return this.didChangeConfiguration;
  }

  /**
   * Capabilities specific to the `workspace/didChangeConfiguration` notification.
   */
  public void setDidChangeConfiguration(final DidChangeConfigurationCapabilities didChangeConfiguration) {
    this.didChangeConfiguration = didChangeConfiguration;
  }

  /**
   * Capabilities specific to the `workspace/didChangeWatchedFiles` notification.
   */
  public DidChangeWatchedFilesCapabilities getDidChangeWatchedFiles() {
    return this.didChangeWatchedFiles;
  }

  /**
   * Capabilities specific to the `workspace/didChangeWatchedFiles` notification.
   */
  public void setDidChangeWatchedFiles(final DidChangeWatchedFilesCapabilities didChangeWatchedFiles) {
    this.didChangeWatchedFiles = didChangeWatchedFiles;
  }

  /**
   * Capabilities specific to the `workspace/symbol` request.
   */
  public SymbolCapabilities getSymbol() {
    return this.symbol;
  }

  /**
   * Capabilities specific to the `workspace/symbol` request.
   */
  public void setSymbol(final SymbolCapabilities symbol) {
    this.symbol = symbol;
  }

  /**
   * Capabilities specific to the `workspace/executeCommand` request.
   */
  public ExecuteCommandCapabilities getExecuteCommand() {
    return this.executeCommand;
  }

  /**
   * Capabilities specific to the `workspace/executeCommand` request.
   */
  public void setExecuteCommand(final ExecuteCommandCapabilities executeCommand) {
    this.executeCommand = executeCommand;
  }

  /**
   * The client has support for workspace folders.
   * <p>
   * Since 3.6.0
   */
  public Boolean getWorkspaceFolders() {
    return this.workspaceFolders;
  }

  /**
   * The client has support for workspace folders.
   * <p>
   * Since 3.6.0
   */
  public void setWorkspaceFolders(final Boolean workspaceFolders) {
    this.workspaceFolders = workspaceFolders;
  }

  /**
   * The client supports `workspace/configuration` requests.
   * <p>
   * Since 3.6.0
   */
  public Boolean getConfiguration() {
    return this.configuration;
  }

  /**
   * The client supports `workspace/configuration` requests.
   * <p>
   * Since 3.6.0
   */
  public void setConfiguration(final Boolean configuration) {
    this.configuration = configuration;
  }

  /**
   * Capabilities specific to the semantic token requests scoped to the
   * workspace.
   * <p>
   * Since 3.16.0
   */
  public SemanticTokensWorkspaceCapabilities getSemanticTokens() {
    return this.semanticTokens;
  }

  /**
   * Capabilities specific to the semantic token requests scoped to the
   * workspace.
   * <p>
   * Since 3.16.0
   */
  public void setSemanticTokens(final SemanticTokensWorkspaceCapabilities semanticTokens) {
    this.semanticTokens = semanticTokens;
  }

  /**
   * Capabilities specific to the code lens requests scoped to the
   * workspace.
   * <p>
   * Since 3.16.0
   */
  public CodeLensWorkspaceCapabilities getCodeLens() {
    return this.codeLens;
  }

  /**
   * Capabilities specific to the code lens requests scoped to the
   * workspace.
   * <p>
   * Since 3.16.0
   */
  public void setCodeLens(final CodeLensWorkspaceCapabilities codeLens) {
    this.codeLens = codeLens;
  }

  /**
   * The client has support for file requests/notifications.
   * <p>
   * Since 3.16.0
   */
  public FileOperationsWorkspaceCapabilities getFileOperations() {
    return this.fileOperations;
  }

  /**
   * The client has support for file requests/notifications.
   * <p>
   * Since 3.16.0
   */
  public void setFileOperations(final FileOperationsWorkspaceCapabilities fileOperations) {
    this.fileOperations = fileOperations;
  }

  /**
   * Client workspace capabilities specific to inlay hints.
   * <p>
   * Since 3.17.0
   */
  public InlayHintWorkspaceCapabilities getInlayHint() {
    return this.inlayHint;
  }

  /**
   * Client workspace capabilities specific to inlay hints.
   * <p>
   * Since 3.17.0
   */
  public void setInlayHint(final InlayHintWorkspaceCapabilities inlayHint) {
    this.inlayHint = inlayHint;
  }

  /**
   * Client workspace capabilities specific to inline values.
   * <p>
   * Since 3.17.0
   */
  public InlineValueWorkspaceCapabilities getInlineValue() {
    return this.inlineValue;
  }

  /**
   * Client workspace capabilities specific to inline values.
   * <p>
   * Since 3.17.0
   */
  public void setInlineValue(final InlineValueWorkspaceCapabilities inlineValue) {
    this.inlineValue = inlineValue;
  }

  /**
   * Client workspace capabilities specific to diagnostics.
   * <p>
   * Since 3.17.0
   */
  public DiagnosticWorkspaceCapabilities getDiagnostics() {
    return this.diagnostics;
  }

  /**
   * Client workspace capabilities specific to diagnostics.
   * <p>
   * Since 3.17.0
   */
  public void setDiagnostics(final DiagnosticWorkspaceCapabilities diagnostics) {
    this.diagnostics = diagnostics;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("applyEdit", this.applyEdit);
    b.add("workspaceEdit", this.workspaceEdit);
    b.add("didChangeConfiguration", this.didChangeConfiguration);
    b.add("didChangeWatchedFiles", this.didChangeWatchedFiles);
    b.add("symbol", this.symbol);
    b.add("executeCommand", this.executeCommand);
    b.add("workspaceFolders", this.workspaceFolders);
    b.add("configuration", this.configuration);
    b.add("semanticTokens", this.semanticTokens);
    b.add("codeLens", this.codeLens);
    b.add("fileOperations", this.fileOperations);
    b.add("inlayHint", this.inlayHint);
    b.add("inlineValue", this.inlineValue);
    b.add("diagnostics", this.diagnostics);
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
    WorkspaceClientCapabilities other = (WorkspaceClientCapabilities) obj;
    if (this.applyEdit == null) {
      if (other.applyEdit != null)
        return false;
    } else if (!this.applyEdit.equals(other.applyEdit))
      return false;
    if (this.workspaceEdit == null) {
      if (other.workspaceEdit != null)
        return false;
    } else if (!this.workspaceEdit.equals(other.workspaceEdit))
      return false;
    if (this.didChangeConfiguration == null) {
      if (other.didChangeConfiguration != null)
        return false;
    } else if (!this.didChangeConfiguration.equals(other.didChangeConfiguration))
      return false;
    if (this.didChangeWatchedFiles == null) {
      if (other.didChangeWatchedFiles != null)
        return false;
    } else if (!this.didChangeWatchedFiles.equals(other.didChangeWatchedFiles))
      return false;
    if (this.symbol == null) {
      if (other.symbol != null)
        return false;
    } else if (!this.symbol.equals(other.symbol))
      return false;
    if (this.executeCommand == null) {
      if (other.executeCommand != null)
        return false;
    } else if (!this.executeCommand.equals(other.executeCommand))
      return false;
    if (this.workspaceFolders == null) {
      if (other.workspaceFolders != null)
        return false;
    } else if (!this.workspaceFolders.equals(other.workspaceFolders))
      return false;
    if (this.configuration == null) {
      if (other.configuration != null)
        return false;
    } else if (!this.configuration.equals(other.configuration))
      return false;
    if (this.semanticTokens == null) {
      if (other.semanticTokens != null)
        return false;
    } else if (!this.semanticTokens.equals(other.semanticTokens))
      return false;
    if (this.codeLens == null) {
      if (other.codeLens != null)
        return false;
    } else if (!this.codeLens.equals(other.codeLens))
      return false;
    if (this.fileOperations == null) {
      if (other.fileOperations != null)
        return false;
    } else if (!this.fileOperations.equals(other.fileOperations))
      return false;
    if (this.inlayHint == null) {
      if (other.inlayHint != null)
        return false;
    } else if (!this.inlayHint.equals(other.inlayHint))
      return false;
    if (this.inlineValue == null) {
      if (other.inlineValue != null)
        return false;
    } else if (!this.inlineValue.equals(other.inlineValue))
      return false;
    if (this.diagnostics == null) {
      if (other.diagnostics != null)
        return false;
    } else if (!this.diagnostics.equals(other.diagnostics))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.applyEdit== null) ? 0 : this.applyEdit.hashCode());
    result = prime * result + ((this.workspaceEdit== null) ? 0 : this.workspaceEdit.hashCode());
    result = prime * result + ((this.didChangeConfiguration== null) ? 0 : this.didChangeConfiguration.hashCode());
    result = prime * result + ((this.didChangeWatchedFiles== null) ? 0 : this.didChangeWatchedFiles.hashCode());
    result = prime * result + ((this.symbol== null) ? 0 : this.symbol.hashCode());
    result = prime * result + ((this.executeCommand== null) ? 0 : this.executeCommand.hashCode());
    result = prime * result + ((this.workspaceFolders== null) ? 0 : this.workspaceFolders.hashCode());
    result = prime * result + ((this.configuration== null) ? 0 : this.configuration.hashCode());
    result = prime * result + ((this.semanticTokens== null) ? 0 : this.semanticTokens.hashCode());
    result = prime * result + ((this.codeLens== null) ? 0 : this.codeLens.hashCode());
    result = prime * result + ((this.fileOperations== null) ? 0 : this.fileOperations.hashCode());
    result = prime * result + ((this.inlayHint== null) ? 0 : this.inlayHint.hashCode());
    result = prime * result + ((this.inlineValue== null) ? 0 : this.inlineValue.hashCode());
    return prime * result + ((this.diagnostics== null) ? 0 : this.diagnostics.hashCode());
  }
}
